package com.company.DBO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static Connection connection = null;

    public static Connection createConnection(){
        if(connection != null) return connection;

        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/BOOKSTORE?useSSL=false";
        String user = "root";
        String password = "@ahmed1102001";

        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url,user,password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

}
