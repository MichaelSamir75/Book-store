package com.DBO;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
            List<String> authors = getBookAuthors(isbn);
            Book book = new Book(isbn, title, authors, publisher, publicationYear, price, category, numOfCopies, threshold);
            allBooks.put(isbn, book);
        }
        return allBooks;
    }

    public static List<String> getBookAuthors(int isbn) throws SQLException {
        Connection connection = DBConnection.createConnection();
        String query = "SELECT authorName FROM AUTHOR JOIN BOOK_AUTHORS ON AUTHOR.authorId = BOOK_AUTHORS.authorId WHERE BOOK_AUTHORS.isbn = ?;";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, isbn);
        ResultSet result = statement.executeQuery();
        List<String> authors = new ArrayList<>();
        while(result.next()) {
            authors.add(result.getString(1));
        }
        return authors;
    }

    public static HashMap<Integer, Book> getMatchingBooks(String searchTerm, String searchAttr) throws SQLException {
        Connection connection = DBConnection.createConnection();
        HashMap<Integer, Book> matchingBooks = new HashMap<>();
        String query = "SELECT * FROM bookstore.BOOK WHERE " + searchAttr + " LIKE ?;";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, "%" + searchTerm + "%");
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
            List<String> authors = getBookAuthors(isbn);
            Book book = new Book(isbn, title, authors, publisher, publicationYear, price, category, numOfCopies, threshold);
            matchingBooks.put(isbn, book);
        }
        return matchingBooks;
    }

    public static HashMap<Integer, Book> searchByAuthor(String searchTerm) throws SQLException {
        Connection connection = DBConnection.createConnection();
        HashMap<Integer, Book> matchingBooks = new HashMap<>();
        String query = "SELECT Book.isbn, title, publisherName, publicationYear, sellingPrice, category, numOfCopies, threshold " +
                "FROM AUTHOR JOIN BOOK_AUTHORS ON AUTHOR.authorId = BOOK_AUTHORS.authorId " +
                "JOIN BOOK ON BOOK_AUTHORS.isbn = BOOK.isbn " +
                "WHERE AUTHOR.authorName LIKE ?;";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, "%" + searchTerm + "%");
        ResultSet result = statement.executeQuery();
        while(result.next()) {
            int isbn = result.getInt(1);
            List<String> authors = getBookAuthors(isbn);
            String title = result.getString(2);
            String publisher = result.getString(3);
            String publicationYear = result.getString(4);
            double price = result.getDouble(5);
            String category = result.getString(6);
            int numOfCopies = result.getInt(7);
            int threshold = result.getInt(8);
            Book book = new Book(isbn, title, authors, publisher, publicationYear, price, category, numOfCopies, threshold);
            matchingBooks.put(isbn, book);
        }
        return matchingBooks;
    }
    public static HashMap<Integer, Book> searchByPrice(String searchTerm) throws SQLException {
        if(searchTerm.isEmpty()) return getAllBooks();
        Connection connection = DBConnection.createConnection();
        HashMap<Integer, Book> matchingBooks = new HashMap<>();
        String query = "SELECT * FROM bookstore.BOOK WHERE sellingPrice <= ?";
        PreparedStatement statement = connection.prepareStatement(query);
        try {
            statement.setDouble(1, Double.parseDouble(searchTerm));
        } catch(Exception e) {
            System.out.println(e);
            return matchingBooks;
        }
        ResultSet result = statement.executeQuery();
        while(result.next()) {
            int isbn = result.getInt(1);
            List<String> authors = getBookAuthors(isbn);
            String title = result.getString(2);
            String publisher = result.getString(3);
            String publicationYear = result.getString(4);
            double price = result.getDouble(5);
            String category = result.getString(6);
            int numOfCopies = result.getInt(7);
            int threshold = result.getInt(8);
            Book book = new Book(isbn, title, authors, publisher, publicationYear, price, category, numOfCopies, threshold);
            matchingBooks.put(isbn, book);
        }
        return matchingBooks;
    }

    public static HashMap<Integer, Book> searchByIsbn(String searchTerm) throws SQLException {
        if(searchTerm.isEmpty()) return getAllBooks();
        Connection connection = DBConnection.createConnection();
        HashMap<Integer, Book> matchingBooks = new HashMap<>();
        String query = "SELECT * FROM bookstore.BOOK WHERE isbn = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        try {
            statement.setInt(1, Integer.parseInt(searchTerm));
        } catch(Exception e) {
            System.out.println(e);
            return matchingBooks;
        }
        ResultSet result = statement.executeQuery();
        while(result.next()) {
            int isbn = result.getInt(1);
            List<String> authors = getBookAuthors(isbn);
            String title = result.getString(2);
            String publisher = result.getString(3);
            String publicationYear = result.getString(4);
            double price = result.getDouble(5);
            String category = result.getString(6);
            int numOfCopies = result.getInt(7);
            int threshold = result.getInt(8);
            Book book = new Book(isbn, title, authors, publisher, publicationYear, price, category, numOfCopies, threshold);
            matchingBooks.put(isbn, book);
        }
        return matchingBooks;
    }
}
