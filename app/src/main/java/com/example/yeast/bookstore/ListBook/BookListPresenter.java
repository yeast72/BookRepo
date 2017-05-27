package com.example.yeast.bookstore.ListBook;

import com.example.yeast.bookstore.model.Book;
import com.example.yeast.bookstore.model.BookRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by yeast on 3/5/2560.
 */

class BookListPresenter implements Observer{
    private BookListView view;
    private BookRepository repository;

    ArrayList<Book> books;

    public BookListPresenter(BookRepository repository,BookListView view){
        this.repository = repository;
        this.view = view;
    }
    public BookRepository sortByTitle() {
       // Collections.sort(books);
        return repository;
    }

    public BookRepository sortByYear() {
        return  repository;
    }

    public void initialize() {
        repository.addObserver(this);
        repository.fetchAllBooks();
    }
    @Override
    public void update(Observable o, Object arg) {
        if(o == repository) {
            books = new ArrayList<>(repository.getAllBooks());
            view.setBookList(books);
        }
    }



}
