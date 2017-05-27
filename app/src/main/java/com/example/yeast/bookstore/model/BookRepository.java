package com.example.yeast.bookstore.model;

import java.util.List;
import java.util.Observable;

/**
 * Created by yeast on 3/5/2560.
 */

public abstract class BookRepository extends Observable {
    public abstract void fetchAllBooks();
    public abstract List<Book> getAllBooks();

}
