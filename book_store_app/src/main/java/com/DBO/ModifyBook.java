package com.DBO;

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

	public boolean modifyBook(String oddTitle,String title,String authorName,String publisherName,String publicationYear,String sellingPrice,String category,String numOfCopies,String threshold){
		try {
			if(!isTitle(oddTitle)) return false;
			int Isbn = getISBN(oddTitle);
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
			deleteAuthors(Isbn);
			String[] authors = authorName.split(",");
			for(int i=0;i<authors.length;i++) {
				int authorId = getAuthorId(authors[i]);
				if (authorId == 0) {
					addAuthor(authors[i]);
					authorId = getAuthorId(authors[i]);
				}
				insertToBook_Author(Isbn, authorId);
			}
			return true;
		}catch (SQLException e){
			System.out.println(e.getMessage());
			return false;
		}

	}

	public boolean isTitle(String title){
		try{
			String str = "select title from book where title =\""+title+"\"";
			PreparedStatement sql = connection.prepareStatement(str);
			ResultSet resultSet = sql.executeQuery();
			int counter = 0;
			while (resultSet.next()) {
				resultSet.getString(1);
				counter++;
			}
			if(counter == 0) return false;
			return true;
		}catch (SQLException e){
			System.out.println("11111111111"+e.getMessage());
			return false;
		}
	}

	public String[] getContent(String title){
		String[] s = null;
		try{
			String str = "select * from book where title =\""+title+"\"";
			PreparedStatement sql = connection.prepareStatement(str);
			ResultSet resultSet = sql.executeQuery();
			String authorName = getAuthorsName(title);
			while (resultSet.next()) {
				s = new String[8];
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

	public String getAuthorsName(String title){
		int isbn = getISBN(title);
		int[] authorid = new int[100];
		String authorname = "";
		try {
			PreparedStatement sql = connection.prepareStatement("Select authorId from book_authors where isbn = " + isbn);
			ResultSet resultSet = sql.executeQuery();
			int counter = 0;
			while (resultSet.next()) {
				authorid[counter] = resultSet.getInt(1);
				counter++;
			}
			for(int i=0;i<counter;i++) {
				sql = connection.prepareStatement("Select authorName from author where authorId = " + authorid[i]);
				resultSet = sql.executeQuery();
				while (resultSet.next()) {
					authorname += resultSet.getString(1)+",";
				}
			}
			return  authorname.substring(0,authorname.length()-1);
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

	public boolean deleteAuthors(int isbn){
		try {
			String str = "delete from book_authors where isbn = " + isbn;
			PreparedStatement sql = connection.prepareStatement(str);
			sql.execute();
		}catch (SQLException e){
			System.out.println(e.getMessage());
			return false;
		}
		return true;
	}

	public boolean insertToBook_Author(int Isbn,int authorId){
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
		} catch (Exception e){
			System.out.println(e.getMessage());
		}
		return 0;
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
}