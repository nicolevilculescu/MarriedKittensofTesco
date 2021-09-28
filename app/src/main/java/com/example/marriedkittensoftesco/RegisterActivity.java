package com.example.marriedkittensoftesco;

import androidx.appcompat.app.AppCompatActivity;
import com.example.marriedkittensoftesco.QuizDbHelper.*;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class RegisterActivity extends AppCompatActivity {

    private EditText Username, Password1, Fname, Lname, Password2;
    private TextView Info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final List<User> users = loadUsers();

        Username = (EditText) findViewById(R.id.username);
        Fname = (EditText) findViewById(R.id.fname);
        Lname = (EditText) findViewById(R.id.lname);
        Password1 = (EditText) findViewById(R.id.password1);
        Password2 = (EditText) findViewById(R.id.password2);

        Info = (TextView) findViewById(R.id.incorrect);
        Info.setVisibility(View.INVISIBLE);
        Button sign_in = (Button) findViewById(R.id.sign_in);
        sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate(Username.getText().toString(), Fname.getText().toString(), Lname.getText().toString(),
                        Password1.getText().toString(), Password2.getText().toString(), users);
            }
        });
    }

    private List<User> loadUsers() {
        QuizDbHelper dbHelper = QuizDbHelper.getInstance(this);
        return dbHelper.getAllUsers();
    }

    private void validate(String Username, String Fname, String Lname, String Password1, String Password2, List<User> users) {
        boolean ok = true;
        if(Username.length() > 0 && Fname.length() > 0 && Lname.length() > 0 && Password1.length() > 0 && Password2.length() > 0) {
            for (User u: users) {
                if (Username.equals(u.getUsername())) {
                    Info.setVisibility(View.VISIBLE);
                    Info.setText("Username taken!");
                    ok = false;
                    break;
                }
            }
            if (ok) {
                if(Password1.equals(Password2)) {
                    QuizDbHelper dbHelper = QuizDbHelper.getInstance(this);
                    User user = new User(Username, Password1, Fname, Lname, 1);
                    dbHelper.addUser(user);
                    int id = dbHelper.findId(Username);
                    dbHelper.addHighscore(id);
                    Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    Info.setVisibility(View.VISIBLE);
                    Info.setText("Passwords do not match!");
                }
            }
        } else {
            Info.setVisibility(View.VISIBLE);
            Info.setText("All fields must be filled!");
        }
    }
}