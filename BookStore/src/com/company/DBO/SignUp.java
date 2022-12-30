package com.company.DBO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SignUp {
    private Connection connection;

    public SignUp() {
        connection = DBConnection.createConnection();
    }

    public Boolean run(String username,String password,String firstName,String lastName,String email,String phone,String shippingAddress){
        try {
            String str = "insert into USER_INFORMATION(userName,password,phone,first_name,last_name,email,Shipping_address) values("
                    + "\"" + username + "\","
                    + "\"" + password + "\","
                    + "\"" + phone + "\","
                    + "\"" + firstName + "\","
                    + "\"" + lastName + "\","
                    + "\"" + email + "\","
                    + "\"" + shippingAddress + "\")";
            PreparedStatement sql = connection.prepareStatement(str);
            sql.execute();
            return true;
        }catch (SQLException e){
            System.out.println(e.getMessage());
            return false;
        }
    }
}
