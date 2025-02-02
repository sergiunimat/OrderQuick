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
                         //check if the credentials do match
                         if(cuser.getCustomerName()==null){
                             Toast.makeText(LogIn.this, "User name or password is incorrect", Toast.LENGTH_LONG).show();
                         }
                         else {
                             //init global user. when login
                             APPLICATION_CURRENT_USER = cuser;
                             /*I: if user is customer*/
                             if (APPLICATION_CURRENT_USER.getRole() == 3) {
                                 Toast.makeText(LogIn.this, "Wellcome "+APPLICATION_CURRENT_USER.getCustomerName(), Toast.LENGTH_LONG).show();
                                 Intent maincustomerView = new Intent(v.getContext(), MainCustomerView.class);
                                 startActivity(maincustomerView);
                             }
                             /*I: if user is admin*/
                             else if(APPLICATION_CURRENT_USER.getRole() == 0){
                                 Toast.makeText(LogIn.this, "Wellcome "+APPLICATION_CURRENT_USER.getCustomerName(), Toast.LENGTH_LONG).show();
                                 Intent mainAdminActivity = new Intent(v.getContext(), MainAdminActivity.class);
                                 startActivity(mainAdminActivity);
                             }
                             /*I: if user is employee*/
                             else if (APPLICATION_CURRENT_USER.getRole() == 2){
                                 Toast.makeText(LogIn.this, "Wellcome "+APPLICATION_CURRENT_USER.getCustomerName(), Toast.LENGTH_LONG).show();
                                 Intent mainAdminActivity = new Intent(v.getContext(), EmployeeMainActivity.class);
                                 startActivity(mainAdminActivity);
                             }
                         }
                     }
                     else {
                         Toast.makeText(LogIn.this, "incorrect user credentials", Toast.LENGTH_LONG).show();
                     }
                 }
             }
         });
    }
}
