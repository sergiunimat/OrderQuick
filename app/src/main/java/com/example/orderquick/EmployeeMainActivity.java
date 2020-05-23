package com.example.orderquick;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class EmployeeMainActivity extends BaseAppClass {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_main);

        if (APPLICATION_CURRENT_USER.getPassword().equals("employee")){
            Intent redirect  = new Intent(this,CustomerProfile.class);
            startActivity(redirect);
        }
        {
            /*I: show list of orders.*/
        }
    }
}
