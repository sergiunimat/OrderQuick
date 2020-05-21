package com.example.orderquick;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LogIn extends BaseAppClass {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);


        Button registerBtn = (Button) findViewById(R.id.regBtn);
        Button logInBtn = (Button) findViewById(R.id.loginBtn);
        final EditText username = (EditText) findViewById(R.id.edUserRegNameLogin);
        final EditText password = (EditText) findViewById(R.id.edUserRegPassLogin);

        //onclick
         registerBtn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent register = new Intent(v.getContext(),MainActivity.class);
                 startActivity(register);
             }
         });
         logInBtn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 if (TextUtils.isEmpty(username.getText().toString())||TextUtils.isEmpty(password.getText().toString())){
                     Toast.makeText(LogIn.this, "Note, user name and password must be provided", Toast.LENGTH_LONG).show();
                 }
                 else {
                     //call the query and initialize global variables
                     DBHelper dbHelper = new DBHelper(LogIn.this);
                     CustomerModel cuser = new CustomerModel();
                     cuser = dbHelper.Login(username.getText().toString(), password.getText().toString());
                     if (cuser!=null){
                         Toast.makeText(LogIn.this, "User logged ing", Toast.LENGTH_LONG).show();
                         //init global user. when login
                         APPLICATION_CURRENT_USER = cuser;
                         // - if global user role is 3 - go customer views
                         if (APPLICATION_CURRENT_USER.getRole()==3){
                             Intent maincustomerView = new Intent(v.getContext(),MainCustomerView.class);
                             startActivity(maincustomerView);
                         }
                         // - if global user role is 2 - go restaurant views
                         // - if global user role is 1 - go admin views
                     }
                     else {
                         Toast.makeText(LogIn.this, "incorrect user credentials", Toast.LENGTH_LONG).show();
                     }
                 }
             }
         });
    }
}
