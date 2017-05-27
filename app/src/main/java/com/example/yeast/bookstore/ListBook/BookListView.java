package com.example.yeast.bookstore.ListBook;

import android.view.View;

import com.example.yeast.bookstore.model.Book;

import java.util.ArrayList;

/**
 * Created by yeast on 3/5/2560.
 */

public interface BookListView {
        void setBookList(ArrayList<Book> books);
        void onRatioButtonClicked(View view);

}
