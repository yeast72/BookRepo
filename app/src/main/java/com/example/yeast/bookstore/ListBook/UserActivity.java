package com.example.yeast.bookstore.ListBook;

/**
 * Created by preawbnp on 5/4/2017 AD.
 */

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.yeast.bookstore.R;

import static com.example.yeast.bookstore.ListBook.MainActivity.user;

public class UserActivity extends AppCompatActivity {
    TextView balanceText;
    Button fundBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);


        balanceText = (TextView) findViewById(R.id.balanceText);
        fundBtn = (Button) findViewById(R.id.fundBtn);

        balanceText.setText(String.format("%.2f",user.getBalance()));
    }


    public void addFund(View view) {
        user.setBalance(user.getBalance() + 100);
        balanceText.setText(String.format("%.2f",user.getBalance()));

    }

    public void goToUserBookList(View view) {
        Intent intent = new Intent(this, UserBookListActivity.class);
        startActivity(intent);
    }
}
