package com.mukuljangir.android.loginapplication;

import android.content.Intent;
import android.database.sqlite.SQLiteException;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.sql.SQLException;

public class MainActivity extends AppCompatActivity {
    private Button login_btn,signUp_btn;
    private TextInputLayout email_editext,password_edittext;
    DatabaseHandler dh;
    private boolean emailAlreadyExists,passwordAlreadyExists;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login_btn = (Button) findViewById(R.id.login_Button);
        signUp_btn = (Button) findViewById(R.id.signUp_Button);
        email_editext = (TextInputLayout) findViewById(R.id.email);
        password_edittext = (TextInputLayout) findViewById(R.id.password);

        //adding listener to signUp button
        signUp_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              startRegisterActivity();
            }
        });

        //adding listener to login button
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(email_editext.getEditText().getText().toString().trim().equals("") || password_edittext.getEditText().getText().toString().trim().equals("")) {
                    Toast.makeText(getApplicationContext(),"Fill above info",Toast.LENGTH_SHORT).show();
                }else
                {
                    dh = new DatabaseHandler(getApplicationContext());
                    String value1 = email_editext.getEditText().getText().toString().trim();
                    String value2 = password_edittext.getEditText().getText().toString().trim();
                    emailAlreadyExists = dh.checkEmail(value1);
                    passwordAlreadyExists = dh.checkPassword(value2);
                    }
                    if(emailAlreadyExists == true && passwordAlreadyExists == true) {
                        startProfileActivity();
                    }else{
                        Toast.makeText(getApplicationContext(),"Register first",Toast.LENGTH_SHORT).show();
                    }
                }
        });

}
    public void startRegisterActivity() {
        Intent intent = new Intent(this,RegisterActivity.class);
        startActivity(intent);
    }
    public void startProfileActivity() {
        Intent intent = new Intent(this,ProfileActivity.class);
        startActivity(intent);
    }

}
