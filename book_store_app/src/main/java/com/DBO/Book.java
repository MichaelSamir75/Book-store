package com.DBO;

public class Book {
    private int isbn;
    private String title;
    private String publisher;
    private String publicationYear;
    private double price;
    private String category;
    private int numOfCopies;
    private int threshold;

    public Book(int isbn, String title, String publisher, String publicationYear, double price, String category, int numOfCopies, int threshold) {
        this.isbn = isbn;
        this.title = title;
        this.publisher = publisher;
        this.publicationYear = publicationYear;
        this.price = price;
        this.category = category;
        this.numOfCopies = numOfCopies;
        this.threshold = threshold;
    }

    public int getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getPublicationYear() {
        return publicationYear;
    }

    public double getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }

    public int getNumOfCopies() {
        return numOfCopies;
    }

    public int getThreshold() {
        return threshold;
    }
}
