package com.example.orderquick;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CustomerProfile extends BaseAppClass {


    Button edit_user;
    EditText edit_user_name;
    EditText edit_user_tel;
    EditText edit_user_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_profile);

        edit_user = (Button)findViewById(R.id.edit_user_button_id);
        edit_user_name = (EditText)findViewById(R.id.edit_username_id);
        edit_user_tel = (EditText)findViewById(R.id.edit_usertel_id);
        edit_user_password = (EditText)findViewById(R.id.edit_userpassword_id);

        if (APPLICATION_CURRENT_USER!=null){
            edit_user_name.setText(APPLICATION_CURRENT_USER.getCustomerName());
            edit_user_tel.setText(APPLICATION_CURRENT_USER.getTelephoneNumber());
        }

        edit_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomerModel customerModel = new CustomerModel();
                DBHelper dbh = new DBHelper(v.getContext());
                customerModel.setRole(3);
                customerModel.setPassword(edit_user_password.getText().toString());
                customerModel.setTelephoneNumber(edit_user_tel.getText().toString());
                customerModel.setCustomerName(edit_user_name.getText().toString());
                customerModel.setCustomerId(APPLICATION_CURRENT_USER.getCustomerId());
                customerModel.setWage(APPLICATION_CURRENT_USER.getWage());
                boolean b = dbh.EditCustomer(customerModel);
                if (b){
                    Toast.makeText(CustomerProfile.this, "Update Success", Toast.LENGTH_SHORT).show();
                    Intent login = new Intent(v.getContext(),LogIn.class);
                    //do not allow the user to go back to previous activity
                    login.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK |Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(login);

                }
                else {
                    Toast.makeText(CustomerProfile.this, "Update Fail", Toast.LENGTH_SHORT).show();
                }

                //1.create a function in the database that updated the records
                //redirect user to log in + finish()
            }
        });
    }
}
