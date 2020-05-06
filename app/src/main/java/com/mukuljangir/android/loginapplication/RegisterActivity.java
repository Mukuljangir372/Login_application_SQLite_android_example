package com.mukuljangir.android.loginapplication;

import android.content.Intent;
import android.database.Cursor;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    private Button login_btn,register_btn;
    private TextInputLayout email_editText,password_editText,confirmPassword_editText;

    DatabaseHandler databaseHandler;
    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_layout);
        login_btn = (Button) findViewById(R.id.login_Button);
        register_btn = (Button) findViewById(R.id.register_button);
        email_editText = (TextInputLayout) findViewById(R.id.email);
        password_editText = (TextInputLayout)findViewById(R.id.password);
        confirmPassword_editText = (TextInputLayout) findViewById(R.id.confirmPassword);

        //getting intent from MainActivity
        Intent intent = getIntent();

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        //inserting data
        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(email_editText.getEditText().getText().toString().trim().equals("") || password_editText.getEditText().getText().toString().trim().equals("") || confirmPassword_editText.getEditText().getText().toString().trim().equals("")) {
                    Toast.makeText(getApplicationContext(),"Fill above info",Toast.LENGTH_SHORT).show();
                }else {
                    if(password_editText.getEditText().getText().toString().trim().equals(confirmPassword_editText.getEditText().getText().toString().trim())) {

                        //inserting data to database
                        databaseHandler = new DatabaseHandler(getApplicationContext());
                        user = new User();
                        user.setEmail(email_editText.getEditText().getText().toString().trim());
                        user.setPassword(password_editText.getEditText().getText().toString().trim());
                        boolean insertResult = databaseHandler.addUser(user);
                        if(insertResult) {
                            Toast.makeText(getApplicationContext(),"Registration completed",Toast.LENGTH_SHORT).show();
                            onBackPressed();
                        }else {
                            Toast.makeText(getApplicationContext(),"Registration not completed",Toast.LENGTH_SHORT).show();
                        }

                    }else{
                        Toast.makeText(getApplicationContext(),"Confirm password not match",Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
    }
}
