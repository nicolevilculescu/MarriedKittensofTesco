package com.example.marriedkittensoftesco;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_ID = "extraId";

    private EditText Username, Password;
    private TextView Info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final List<User> users = loadUsers();

        Username = (EditText) findViewById(R.id.username);
        Password = (EditText) findViewById(R.id.password);
        Info = (TextView) findViewById(R.id.incorrect);
        Info.setVisibility(View.INVISIBLE);
        Button log_in = (Button) findViewById(R.id.log_in);
        TextView sign_in = (TextView) findViewById(R.id.sing_in);

        log_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate(Username.getText().toString(), Password.getText().toString(), users);
            }
        });
        sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    private List<User> loadUsers() {
        QuizDbHelper dbHelper = QuizDbHelper.getInstance(this);
        return dbHelper.getAllUsers();
    }

    private void validate(String Username, String Password, List<User> users) {
        boolean ok = false;
        for (User u: users) {
            if (Username.equals(u.getUsername()) && Password.equals(u.getPassword())) {
                Info.setVisibility(View.INVISIBLE);
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                QuizDbHelper dbHelper = QuizDbHelper.getInstance(this);
                intent.putExtra(EXTRA_ID, dbHelper.findId(Username));
                startActivityForResult(intent, 0);
                ok = true;
                break;
            }
        }
        if (!ok)
            Info.setVisibility(View.VISIBLE);
    }
}