package com.DBO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Order {
    private Connection connection;
    public Order() {
        connection = DBConnection.createConnection();
    }
    public boolean placeOrder(String bookName , String q , String mangerEmail){
      int id=0 ;
      int isbn =0; int quantity = Integer.parseInt(q);
      try {
          PreparedStatement sql = connection.prepareStatement("Select isbn from BOOK where title = " + "\"" + bookName + "\"");
          ResultSet resultSet = sql.executeQuery();
          while (resultSet.next()) {
              isbn=resultSet.getInt(1);
          }
          PreparedStatement sql2 = connection.prepareStatement("Select userId from USER_INFORMATION where email = " + "\"" + mangerEmail + "\"");
          ResultSet resultSet2 = sql2.executeQuery();
          while (resultSet2.next()) {
              id=resultSet2.getInt(1);
          }
      }catch (Exception e){
         System.out.println(e.getMessage());
        return false;
      }
      try {
          PreparedStatement sql = connection.prepareStatement("INSERT INTO ORDERS (`userId`, `isbn`, `quantity`) VALUES ("+id+","+isbn+","+quantity+")");
          sql.execute();

      }catch (SQLException e){
          System.out.println(e.getMessage());
          return false;
      }
      return true ;
  }
    public ArrayList<String[]> ShowOrders(){
        ArrayList<String[]> res = new ArrayList<>();
        try {
            PreparedStatement sql = connection.prepareStatement("Select ORDERS.orderId , USER_INFORMATION.userName , BOOK.title , ORDERS.quantity from BOOK , ORDERS , USER_INFORMATION where ORDERS.userId = USER_INFORMATION.userId and BOOK.isbn=ORDERS.isbn ");
            ResultSet resultSet = sql.executeQuery();
            while (resultSet.next()) {
               String[] currOrder = new String[4]; //[orderId , Manger name , book name , bookQuantity]
               currOrder[0]=Integer.toString(resultSet.getInt(1));
               currOrder[1]=resultSet.getString(2);
               currOrder[2]=resultSet.getString(3);
               currOrder[3]=Integer.toString(resultSet.getInt(4));
               res.add(currOrder);
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return res;
    }

    public Boolean ConfirmOrders(String oId){
        int orderId = Integer.parseInt(oId);
        try {
            PreparedStatement sql = connection.prepareStatement("delete from orders where orderId = " + orderId);
            sql.execute();
            return true;
        }catch (SQLException e){
            System.out.println(e.getMessage());
            return false;
        }
    }

}
