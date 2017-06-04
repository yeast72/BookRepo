package com.example.yeast.bookstore.ListBook;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.yeast.bookstore.R;
import com.example.yeast.bookstore.model.User;

public class UserActivity extends AppCompatActivity {
    private User user;
    TextView balanceText;
    Button fundBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        user = new User();

        balanceText = (TextView) findViewById(R.id.balanceText);
        fundBtn = (Button) findViewById(R.id.fundBtn);

        balanceText.setText(user.getBalance() + "");
    }


    public void addFund(View view) {
        user.setBalance(user.getBalance() + 100);
        balanceText.setText(user.getBalance() + "");
    }
}
