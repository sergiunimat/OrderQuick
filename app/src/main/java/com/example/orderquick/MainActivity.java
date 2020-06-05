package com.example.orderquick;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.Console;

public class MainActivity extends BaseAppClass {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //init vars.
        Button register = (Button) findViewById(R.id.registerBtn);
        Button login = (Button) findViewById(R.id.loginBtn);
        final EditText userName = (EditText) findViewById(R.id.edUserRegName);
        final EditText userPassword =(EditText) findViewById(R.id.edUserRegPass);
        final EditText userTelNr = (EditText) findViewById(R.id.edUserRegTell) ;
        final EditText userpaPasswordMatch = (EditText) findViewById(R.id.edUserRegPassConf) ;

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomerModel customerModel;
                try {
                    if(TextUtils.isEmpty(userName.getText().toString())||
                            TextUtils.isEmpty(userTelNr.getText().toString())||
                            TextUtils.isEmpty(userPassword.getText().toString())||
                            TextUtils.isEmpty(userpaPasswordMatch.getText().toString()))
                    {
                        Toast.makeText(MainActivity.this, "Note, All the fields must be given", Toast.LENGTH_SHORT).show();
                    }
                    else if (!userPassword.getText().toString().equals(userpaPasswordMatch.getText().toString())){
                        Toast.makeText(MainActivity.this, "Password Do not match", Toast.LENGTH_SHORT).show();
                    }
                    else {

                        customerModel = new CustomerModel(-1,userName.getText().toString(),
                                userPassword.getText().toString(),
                                userTelNr.getText().toString(),
                                3,
                                "zero");

                        DBHelper dbHelper = new DBHelper(MainActivity.this);
                        boolean userTelExists = dbHelper.UniqueUserTel(userTelNr.getText().toString());
                        if(userTelExists){
                            Toast.makeText(MainActivity.this, "This telephone number already exists", Toast.LENGTH_LONG).show();
                            userTelNr.setSelection(0);

                        }
                        else {
                            boolean check = dbHelper.AddCustomerToDb(customerModel);
                            if (check){
                                Toast.makeText(MainActivity.this, "Pleas sign in", Toast.LENGTH_SHORT).show();
                                Intent login = new Intent(MainActivity.this,LogIn.class);
                                startActivity(login);
                            }else {
                                Toast.makeText(MainActivity.this, "Error while adding customer to the system.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }catch (Exception e){
                    Log.i("Info",e.toString());
                }
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent login = new Intent(v.getContext(),LogIn.class);
                startActivity(login);
            }
        });
    }
}
