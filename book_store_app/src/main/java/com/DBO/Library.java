package com.DBO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class Library {
    public static HashMap<Integer, Book> getAllBooks() throws SQLException {
        Connection connection = DBConnection.createConnection();
        HashMap<Integer, Book> allBooks = new HashMap<>();
        String query = "SELECT * FROM bookstore.BOOK;";
        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet result = statement.executeQuery();
        while(result.next()) {
            int isbn = result.getInt(1);
            String title = result.getString(2);
            String publisher = result.getString(3);
            String publicationYear = result.getString(4);
            double price = result.getDouble(5);
            String category = result.getString(6);
            int numOfCopies = result.getInt(7);
            int threshold = result.getInt(8);
            Book book = new Book(isbn, title, publisher, publicationYear, price, category, numOfCopies, threshold);
            allBooks.put(isbn, book);
        }
        return allBooks;
    }
}
