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

	public boolean modifyBook(String authorName,String title,String publisherName,String publicationYear,String sellingPrice,String category,String numOfCopies,String threshold){
		try {
			int Isbn = getISBN(title);
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

    public String[] getContent(String title){
		String[] s = new String[8];
		try{
			String str = "select * from book where title =\""+title+"\"";
			PreparedStatement sql = connection.prepareStatement(str);
			ResultSet resultSet = sql.executeQuery();
			String authorName = getAuthotorName(title);
			while (resultSet.next()) {
				s[0] = resultSet.getString(2);
				s[1] = authorName;
				s[2] = resultSet.getString(3);
				s[3] = resultSet.getString(4);
				s[4] = resultSet.getString(5);
				s[5] = resultSet.getString(6);
				s[6] = resultSet.getString(7);
				s[7] = resultSet.getString(8);
			}

		}catch (SQLException e){
			System.out.println(e.getMessage());

		}
		return s;
	}
    public String getAuthotorName(String title){
		int isbn = getISBN(title);
		int authorid = 0;
		String authorname = "";
		try {
			PreparedStatement sql = connection.prepareStatement("Select authorId from book_authors where isbn = " + isbn);
			ResultSet resultSet = sql.executeQuery();
			while (resultSet.next()) {
				authorid = resultSet.getInt(1);
			}
			sql = connection.prepareStatement("Select authorName from author where authorId = " + authorid);
			resultSet = sql.executeQuery();
			while (resultSet.next()) {
				authorname = resultSet.getString(1);
			}
			return  authorname;
		}catch (SQLException e){
			System.out.println(e.getMessage());
			return "";
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
