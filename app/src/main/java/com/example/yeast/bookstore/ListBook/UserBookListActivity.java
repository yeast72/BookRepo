package com.example.yeast.bookstore.ListBook;
/**
 * Created by preawbnp on 5/4/2017 AD.
 */

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.yeast.bookstore.R;
import com.example.yeast.bookstore.model.Book;

public class UserBookListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_book_list);

        ListView  myList = (ListView) findViewById(R.id.cartListView);
        ArrayAdapter<Book> listArrayAdapter = new ArrayAdapter<Book>(this, android.R.layout.simple_list_item_1, MainActivity.user.getBooksList());
        myList.setAdapter(listArrayAdapter);
    }
}
