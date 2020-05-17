package com.example.orderquick;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomerModel customerModel;
                try {
                    customerModel = new CustomerModel(-1,userName.getText().toString(),
                            userTelNr.getText().toString(),
                            userPassword.getText().toString(),
                            3);
                    DBHelper dbHelper = new DBHelper(MainActivity.this);
                    boolean check = dbHelper.AddCustomerToDb(customerModel);
                    if (check){
                        Toast.makeText(MainActivity.this, "Customer added to database", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(MainActivity.this, "error while adding customer to database.", Toast.LENGTH_SHORT).show();
                    }

                }catch (Exception e){
                    Toast.makeText(MainActivity.this, "Customer NOT added to database", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
