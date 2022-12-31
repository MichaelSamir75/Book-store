package com.company.DBO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class promotion {
	private Connection connection;
	public promotion() {
		connection = DBConnection.createConnection();
	}
	public boolean promote(String email){
		try{
			int userId = getUserId(email);
			String str = "Insert into manager values ( "+ userId+ ")";
			PreparedStatement sql = connection.prepareStatement(str);
			sql.execute();
			return true;
		}catch (SQLException e){
			System.out.println(e.getMessage());
			return false;
		}
	}
	private int getUserId(String email){
		int id =0;
		try {
			PreparedStatement sql = connection.prepareStatement("Select userId from user_information where email = " + "\"" + email + "\"");
			ResultSet resultSet = sql.executeQuery();
			while (resultSet.next()) {
				id=resultSet.getInt(1);
			}
			return id;
		}catch (SQLException e){
			System.out.println(e.getMessage());
		}
		return id;
	}
}
