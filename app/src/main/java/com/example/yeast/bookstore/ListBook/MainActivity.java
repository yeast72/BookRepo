package com.example.yeast.bookstore.ListBook;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.yeast.bookstore.R;
import com.example.yeast.bookstore.model.Book;
import com.example.yeast.bookstore.model.BookRepository;
import com.example.yeast.bookstore.model.JSONBookRepository;
import com.example.yeast.bookstore.model.User;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity implements BookListView{
    public static ArrayList<Book> cartList = new ArrayList<>();
    public static User user = new User();

    BookListPresenter presenter;
    ArrayAdapter<Book> bookArrayAdapter;
    private ListView bookListView;

    EditText inputSearch;
    private Button menuBalance, menuCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BookRepository repository = JSONBookRepository.getInstance();

        bookListView = (ListView) findViewById(R.id.listview_books);
        inputSearch = (EditText) findViewById(R.id.input_search);
        menuBalance = (Button) findViewById(R.id.menuBalance);
        menuCart =  (Button) findViewById(R.id.menuCart);

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
        bookListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                alert.setMessage("Add to cart?");

                alert.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(),"Book is added!", Toast.LENGTH_SHORT).show();
                        cartList.add((Book) bookListView.getItemAtPosition(position));
                    }
                });
                alert.setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(),"Cancel", Toast.LENGTH_SHORT).show();
                    }
                });
                alert.setIcon(android.R.drawable.ic_dialog_alert);
                alert.show();
            }
        });
    }

    @Override
    public void onRatioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId()){
            case R.id.radio_sort_title:
                if(checked) {
                    presenter.sortByTitle();
                }
                break;
            case R.id.radio_sort_year:
                if(checked) {
                    presenter.sortByYear();
                }
                break;
        }
    }

    private ArrayAdapter<Book> createAdapter(ArrayList<Book> books) {
        return new ArrayAdapter<Book>(this,android.R.layout.simple_list_item_1,books);
    }

    public void goToBalance(View view) {
        Intent intent = new Intent(this, UserActivity.class);
        startActivity(intent);
    }

    public void goToCart(View view) {
        Intent intent = new Intent(this, CartActivity.class);
        startActivity(intent);
    }

}
