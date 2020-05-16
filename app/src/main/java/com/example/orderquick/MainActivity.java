package com.example.orderquick;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button register;
    EditText userName;
    EditText userPassword;
    EditText userTelNr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //init vars.
        register.findViewById(R.id.registerBtn);
        userName.findViewById(R.id.edUserRegName);
        userPassword.findViewById(R.id.edUserRegPass);
        userTelNr.findViewById(R.id.edUserRegTell);
        //event listeners.
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //code when the register button is clicked.
                Toast.makeText(MainActivity.this, "Register button was pressed", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
