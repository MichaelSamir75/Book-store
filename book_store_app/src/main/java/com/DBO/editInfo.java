package com.DBO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class editInfo {
    private Connection connection;

    public editInfo() {
        connection = DBConnection.createConnection();
    }

    public Boolean run(String values[] , int userId){
        try {
            String str = "UPDATE USER_INFORMATION SET userName = ? , phone = ? , first_name = ? ,last_name = ? , Shipping_address = ? WHERE userId = ? " ;
            PreparedStatement sql = connection.prepareStatement(str);
            sql.setString(1,values[0]) ;
            sql.setString(2,values[1]) ;
            sql.setString(3,values[2]) ;
            sql.setString(4,values[3]) ;
            sql.setString(5,values[4]) ;
            sql.setInt(6,userId) ;
            sql.execute();
            return true;
        }catch (SQLException e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public String getOldPassword(int userId){
        try {
            String query = "SELECT password FROM user_information WHERE userId = ?" ;
            PreparedStatement sql = connection.prepareStatement(query) ;
            sql.setInt(1,userId) ;
            ResultSet resultSet = sql.executeQuery() ;
            String array = null  ;
            while (resultSet.next()){
                array = resultSet.getString(1) ;
            }
            return array ;
        }catch (SQLException e) {
            System.out.println(e.getMessage());
            return null ;
        }
    }

    public boolean saveNewPassword(int userId ,String password){
        try {
            String query = "UPDATE USER_INFORMATION SET password = ? WHERE userId = ?" ;
            PreparedStatement sql = connection.prepareStatement(query) ;
            sql.setString(1,password) ;
            sql.setInt(2,userId) ;
            sql.execute() ;
            return true ;
        }catch (SQLException e) {
            System.out.println(e.getMessage());
            return false ;
        }
    }

    public String[] getUserInfo(int userId) throws SQLException {
            String query = "SELECT * FROM user_information WHERE userId = ?" ;
            PreparedStatement sql = connection.prepareStatement(query) ;
            sql.setInt(1,userId) ;
            ResultSet resultSet = sql.executeQuery() ;
            String[] array = new String[5] ;
            while (resultSet.next()){
                array[0] = resultSet.getString(2) ;
                array[1] = resultSet.getString(4) ;
                array[2] = resultSet.getString(5) ;
                array[3] = resultSet.getString(6) ;
                array[4] = resultSet.getString(8) ;
            }
            return  array ;
    }

}
