package com.DBO;

import com.frontend.Views.ShoppingCartController;

import java.sql.*;

public class Checkout {
    private static Connection connection;

    public Checkout() throws SQLException {
        createConnection();
    }

    public boolean availableQuantity() throws SQLException {
        boolean flag = true;
        for (int i = 0; i < ShoppingCartController.items.size(); i++) {
            String[] item = ShoppingCartController.items.get(i);
            String sql = "select numOfCopies from BOOK where isbn = "  + "\"" + item[0] + "\"";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet copies = statement.executeQuery();
            copies.next();
            if(Integer.parseInt(item[7]) > copies.getInt("numOfCopies")) {
                flag = false;
                if(copies.getInt("numOfCopies") == 0) {
                    ShoppingCartController.items.remove(i);
                    i--;
                }
                else ShoppingCartController.items.get(i)[7] = String.valueOf(copies.getInt("numOfCopies"));
            }
        }

        if(flag) {
            reserveCopies();
            return true;
        }
        System.out.println("closing");
        connection.rollback();
        connection.close();
        return false;
    }

    private void createConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/BOOKSTORE?useSSL=false";
        String user = "root";
        String password = "";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url,user,password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        connection.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
        connection.setAutoCommit(false);
    }

    public void reserveCopies() throws SQLException {
        for (int i = 0; i < ShoppingCartController.items.size(); i++) {
            String[] item = ShoppingCartController.items.get(i);
            String confirm = "update BOOK set numOfCopies = numOfCopies - " + item[7] + " where isbn = " + "\"" + item[0] + "\"";
            System.out.println("confirm: "+ confirm);
            PreparedStatement update = connection.prepareStatement(confirm);
            update.executeUpdate();

            String order = "insert into SELLING_ORDERS values(null, " +  SignIn.userID + ", " + item[0] +
                    ", " + item[7] + ", current_date())";
            System.out.println("order: "+ order);
            PreparedStatement placeOrder = connection.prepareStatement(order);
            placeOrder.executeUpdate();
        }
    }

    public boolean validateCredentials(String cardNo, Date expiryDate) throws SQLException {
        String str = "Select expiryDate from CREDIT_CARD where cardNumber = " + cardNo;
        PreparedStatement sql = connection.prepareStatement(str);
        ResultSet resultSet = sql.executeQuery();
        Date cardDate = null;
        while (resultSet.next()) {
            cardDate = resultSet.getDate("expiryDate");
        }
        if (!expiryDate.equals(cardDate)) {
            return false;
        }
        connection.commit();
        connection.close();
        return true;
    }

    public void rollBack() throws SQLException {
        if(!connection.isClosed()) {
            connection.rollback();
            connection.close();
        }
    }
}
