package com.example.yeast.bookstore.model;

import java.util.ArrayList;

/**
 * Created by preawbnp on 6/4/2017 AD.
 */

public class User {
    private ArrayList<Book> booksList;
    private double balance;

    public User() {
        booksList = new ArrayList<>();
        this.balance = 0;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public ArrayList<Book> getBooksList() {
        return booksList;
    }

}
