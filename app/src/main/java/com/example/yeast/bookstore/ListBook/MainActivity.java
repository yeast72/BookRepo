package com.example.yeast.bookstore.ListBook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Filterable;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import com.example.yeast.bookstore.R;
import com.example.yeast.bookstore.model.Book;
import com.example.yeast.bookstore.model.BookRepository;
import com.example.yeast.bookstore.model.JSONBookRepository;
import com.example.yeast.bookstore.model.MockBookRepository;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity implements BookListView{
    BookListPresenter presenter;
    ArrayAdapter<Book> bookArrayAdapter;
    private ListView bookListView;

    EditText inputSearch;
    private MenuItem menuBalance, menuCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BookRepository repository = JSONBookRepository.getInstance();

        bookListView = (ListView) findViewById(R.id.listview_books);
        inputSearch = (EditText) findViewById(R.id.input_search);
        bookArrayAdapter = createAdapter(new ArrayList<Book>());
        bookListView.setAdapter(bookArrayAdapter);

        presenter = new BookListPresenter(repository,this);
        presenter.initialize();
        inputSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                MainActivity.this.bookArrayAdapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    @Override
    public void setBookList(ArrayList<Book> books) {
        bookArrayAdapter = createAdapter(books);
        bookListView.setAdapter(bookArrayAdapter);
    }

    @Override
    public void onRatioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId()){
            case R.id.radio_sort_title:
                if(checked) {
                    sortByTitle();
            }
                break;
            case R.id.radio_sort_year:
                if(checked) {
                    sortByYear();
                }
                break;
        }

    }

    private void sortByYear() {
        final Collator c = Collator.getInstance();
        bookArrayAdapter.sort(new Comparator<Book>() {
            @Override
            public int compare(Book o1, Book o2) {
                return c.compare(o1.getYear(), o2.getYear());
            }
        });
    }

    private void sortByTitle() {
        final Collator c = Collator.getInstance();
        bookArrayAdapter.sort(new Comparator<Book>() {
            @Override
            public int compare(Book o1, Book o2) {
                return c.compare(o1.getTitle(), o2.getTitle());
            }
        });
    }

    private ArrayAdapter<Book> createAdapter(ArrayList<Book> books) {
        return new ArrayAdapter<Book>(this,android.R.layout.simple_list_item_1,books);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        menuBalance = menu.findItem(R.id.menuBalance);
        menuCart = menu.findItem(R.id.menuCart);

        menuBalance.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                Intent intent = new Intent(MainActivity.this, UserActivity.class);
                startActivity(intent);
                return true;
            }
        });

        menuCart.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                Intent intent = new Intent(MainActivity.this, CartActivity.class);
                startActivity(intent);
                return true;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }
}
