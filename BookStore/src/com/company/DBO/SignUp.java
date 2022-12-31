package com.company.DBO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SignUp {
    private Connection connection;

    public SignUp() {
        connection = DBConnection.createConnection();
    }

    public Boolean run(String values[]){
        try {
            String str = "insert into USER_INFORMATION(userName,password,phone,first_name,last_name,email,Shipping_address) values("
                    + "\"" + values[0] + "\","
                    + "\"" + values[1] + "\","
                    + "\"" + values[2] + "\","
                    + "\"" + values[3] + "\","
                    + "\"" + values[4] + "\","
                    + "\"" + values[5] + "\","
                    + "\"" + values[6] + "\")";
            PreparedStatement sql = connection.prepareStatement(str);
            sql.execute();
            return true;
        }catch (SQLException e){
            System.out.println(e.getMessage());
            return false;
        }
    }
}
