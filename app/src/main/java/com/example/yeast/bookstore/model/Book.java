package com.example.yeast.bookstore.model;

/**
 * Created by yeast on 3/5/2560.
 */

public class Book {
    private int id;
    private String title;
    private double price;
    private int year;
    private String imageUrl;

    public Book(int id, String title, double price, int year) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.year = year;
        this.imageUrl = null;
    }

    public String toString() {
        return title + " " + year + " (" + price + ")";
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public double getPrice() {
        return price;
    }

    public int getYear() {
        return year;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

}
