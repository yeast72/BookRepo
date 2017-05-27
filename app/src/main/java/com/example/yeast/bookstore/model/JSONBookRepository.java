package com.example.yeast.bookstore.model;

import android.os.AsyncTask;

import com.example.yeast.bookstore.model.decoders.BookJSONDecoder;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yeast on 3/5/2560.
 */

public class JSONBookRepository extends BookRepository{
    private List<Book> books;

    private static JSONBookRepository instance = null;

    public static JSONBookRepository getInstance() {
        if(instance == null) {
            return instance = new JSONBookRepository();
        }
        return instance;
    }

    private JSONBookRepository() {
        books = new ArrayList<Book>();
    }

    @Override
    public void fetchAllBooks() {
        BookFetcherTask task = new BookFetcherTask();
        task.execute();
    }

    @Override
    public List<Book> getAllBooks() {
        return books;
    }

    public class BookFetcherTask extends AsyncTask<Void,Void,ArrayList<Book>> {
        @Override
        protected ArrayList<Book> doInBackground(Void... params) {
            String bookListJsonStr = loadJSON();
            if(bookListJsonStr != null) {
                return BookJSONDecoder.createListFromJSONStr(bookListJsonStr);
            } else {
                return null;
            }
        }

        private String loadJSON() {
            URL booksURL = null;
            try {
                booksURL = new URL("https://theory.cpe.ku.ac.th/~jittat/courses/sw-spec/ebooks/books.json");
            } catch (MalformedURLException e) {

            }
            return (new UrlFetcher(booksURL)).fetch();
        }

        @Override
        protected void onPostExecute(ArrayList<Book> results) {
            if(results != null) {
                books.clear();
                books.addAll(results);
                setChanged();
                notifyObservers();
            }
        }
    }

}
