package com.DBO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Checkout {
    private Connection connection;
    public Checkout() {
        connection = DBConnection.createConnection();
    }

    public boolean validateCredentials(String cardNo, Date expiryDate) {
        try {
            String str = "Select expiryDate from CREDIT_CARD where cardNumber = " + "\"" + cardNo + "\"";
            PreparedStatement sql = connection.prepareStatement(str);
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            ResultSet resultSet = sql.executeQuery();
            Date cardDate = null;
            while (resultSet.next()){
                cardDate = resultSet.getDate(0);
            }
            if(expiryDate.equals(cardDate)){
                return true;
            }
            return false;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }
}
