package com.company.DBO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ModifyBook {
	private Connection connection;
	addBook add = new addBook();
	public ModifyBook() {
		connection = DBConnection.createConnection();
	}

	public boolean modifyBook(int Isbn,String authorName,String title,String publisherName,int publicationYear,double sellingPrice,String category,int numOfCopies,int threshold){
		try {
			String str = "update `bookstore`.`book` " +"set title= "+"\"" + title + "\","+
					"publisherName="+"\"" + publisherName + "\","+
					"publicationYear="+"\"" + publicationYear + "\","+
					"sellingPrice="+"\"" + sellingPrice + "\","+
					"category="+"\"" + category + "\","+
					"numOfCopies="+"\"" + numOfCopies + "\","+
					"threshold="+"\"" + threshold + "\""+
					"where isbn = "+"\""+Isbn +"\"";

			PreparedStatement sql = connection.prepareStatement(str);
			sql.execute();
			int authorId = add.getAuthorId(authorName);
			if(authorId == 0){
				add.addAuthor(authorName);
				authorId = add.getAuthorId(authorName);
			}
			modifyAuthorName(Isbn,authorId);

			return true;
		}catch (SQLException e){
			System.out.println(e.getMessage());
			return false;
		}
	}
	public boolean modifyAuthorName(int isbn,int authorId){
		try{
			String str = "update book_authors set authorId= "+"\"" + authorId + "\" where isbn ="+"\"" + isbn + "\"";
			PreparedStatement sql = connection.prepareStatement(str);
			sql.execute();
			return true;
		}catch (SQLException e){
			System.out.println(e.getMessage());
			return false;
	}
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
}
