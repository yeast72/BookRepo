package com.example.yeast.bookstore.ListBook;

/**
 * Created by preawbnp on 5/4/2017 AD.
 */

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yeast.bookstore.R;
import com.example.yeast.bookstore.model.Book;

import java.util.ArrayList;

import static com.example.yeast.bookstore.ListBook.MainActivity.user;
import static com.example.yeast.bookstore.ListBook.MainActivity.cartList;

public class CartActivity extends AppCompatActivity {
    ArrayAdapter<Book> cartArrayAdapter;
    private double balance;

    ListView cartListView;
    Button purchaseBtn;
    TextView balanceText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        cartArrayAdapter = new ArrayAdapter<Book>(this, android.R.layout.simple_list_item_1, MainActivity.cartList);
        cartListView = (ListView) findViewById(R.id.cartListView);
        cartListView.setAdapter(cartArrayAdapter);

        balanceText = (TextView) findViewById(R.id.balanceText);
        purchaseBtn = (Button) findViewById(R.id.purchaseBtn);
        balanceText.setText(user.getBalance() + "");


    }

    public double calculatePrice(ArrayList<Book> books) {
        for (Book b : books)
            balance += b.getPrice();
        return balance;
    }

    public void purchase(View view) {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setMessage("Are you sure?");
        AlertDialog.Builder builder = alert.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

                if (checkCanPurchase() == true) {
                    Toast.makeText(getApplicationContext(), "Completed!", Toast.LENGTH_SHORT).show();
                    balance = calculatePrice(MainActivity.cartList);
                    user.setBalance(user.getBalance() -  balance);
                    balanceText.setText(user.getBalance() + "");
                    for (int i = 0; i < cartList.size(); i++) {
                        user.getBooksList().add(cartList.get(i));
                    }
                    cartList.clear();
                    balance = 0;
                } else {
                    Toast.makeText(getApplicationContext(),"Not completed!", Toast.LENGTH_SHORT).show();
                }
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

    public boolean checkCanPurchase(){
        if (balance < user.getBalance())
            return true;
        return false;
    }
}
