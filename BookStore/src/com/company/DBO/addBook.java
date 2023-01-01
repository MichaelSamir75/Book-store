package com.company.DBO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class addBook {

	private Connection connection;
	public addBook() {
		connection = DBConnection.createConnection();
	}

	public boolean add(String authorName,String title,String publisherName,String publicationYear,String sellingPrice,String category,String numOfCopies,String threshold){
		try {
			String str = "INSERT INTO `bookstore`.`book` (`title`, `publisherName`, `publicationYear`, `sellingPrice`, `category`, `numOfCopies`, `threshold`) VALUES ("
					+ "\"" + title + "\","
					+ "\"" + publisherName + "\","
					+ "\"" + publicationYear + "\","
					+ "\"" + Double.parseDouble(sellingPrice) + "\","
					+ "\"" + category + "\","
					+ "\"" + Integer.parseInt(numOfCopies) + "\","
					+ "\"" + Integer.parseInt(threshold) + "\")";
			PreparedStatement sql = connection.prepareStatement(str);
			sql.execute();
			int isbn = getISBN(title);
			int authorId = getAuthorId(authorName);
			if(authorId == 0){
				addAuthor(authorName);
				authorId = getAuthorId(authorName);
			}
            insertToBook_Author(isbn,authorId);
			return true;
		}catch (SQLException e){
			System.out.println(e.getMessage());
			return false;
		}
	}
	public boolean addAuthor(String authorName){
		try{
			String str = "Insert into `bookstore`.`author` (`authorName`) values ( \"" + authorName + "\" )";
			PreparedStatement sql = connection.prepareStatement(str);
			sql.execute();
			return true;
		}catch (SQLException e){
			System.out.println(e.getMessage());
			return false;
		}
	}
	public int getAuthorId(String authorName){
		try{
			String str = "select authorId from author where authorName =(\""+authorName+"\")";
			PreparedStatement sql = connection.prepareStatement(str);
			ResultSet resultSet = sql.executeQuery();
			int authorId = 0;
			while (resultSet.next()) {
				authorId = resultSet.getInt(1);
			}
			return authorId;
		}catch (SQLException e){
			System.out.println(e.getMessage());
		}
		return 0;
	}

	public int getISBN(String title){
		int isbn =0;
		try {
			PreparedStatement sql = connection.prepareStatement("Select isbn from BOOK where title = " + "\"" + title + "\"");
			ResultSet resultSet = sql.executeQuery();
			while (resultSet.next()) {
				isbn=resultSet.getInt(1);
			}
			return isbn;
		}catch (SQLException e){
			System.out.println(e.getMessage());
		}
		return isbn;
	}
	boolean insertToBook_Author(int Isbn,int authorId){
		try{
			String str = "Insert into book_authors values ( \"" + Isbn + "\" ,+ \"" +authorId +"\")";
			PreparedStatement sql = connection.prepareStatement(str);
			sql.execute();
			return true;
		}catch (SQLException e){
			System.out.println(e.getMessage());
			return false;
		}
	}

}
