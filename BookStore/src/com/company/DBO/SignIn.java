package com.company.DBO;

import java.sql.*;

public class SignIn {
    private Connection connection;

    public SignIn() {
        connection = DBConnection.createConnection();
    }

    public Boolean run(String username,String password){
        try {
            String str = "Select password from USER_INFORMATION where userName = " + "\"" + username + "\"";
            PreparedStatement sql = connection.prepareStatement(str);
            ResultSet resultSet = sql.executeQuery();
            String passw = "";
            while (resultSet.next()){
                passw = resultSet.getString(1);
            }
            if(password.equals(passw)){
                return true;
            }
            return false;
        }catch (SQLException e){
            System.out.println(e.getMessage());
            return false;
        }
    }
}
