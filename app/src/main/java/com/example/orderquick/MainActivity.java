package com.example.orderquick;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //init vars.
        Button register = (Button) findViewById(R.id.registerBtn);
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
                                userTelNr.getText().toString(),
                                userPassword.getText().toString(),
                                3);
                        DBHelper dbHelper = new DBHelper(MainActivity.this);
                        boolean userTelExists = dbHelper.UniqueUserTel(userTelNr.getText().toString());
                        if(userTelExists){
                            Toast.makeText(MainActivity.this, "This telephone number already exists", Toast.LENGTH_LONG).show();
                            userTelNr.setSelection(0);

                        }
                        else {
                            boolean check = dbHelper.AddCustomerToDb(customerModel);
                            if (check){
                                Toast.makeText(MainActivity.this, "Customer added to database", Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(MainActivity.this, "error while adding customer to database.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }catch (Exception e){
                    Toast.makeText(MainActivity.this, "Customer NOT added to database", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
