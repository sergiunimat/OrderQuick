package com.example.orderquick;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class AdminEditCustomerActivuty extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final DBHelper dbH = new DBHelper(this);

        setContentView(R.layout.activity_admin_edit_customer_activuty);
        Intent customerObj = getIntent();
        CustomerViewModel customerViewModel = customerObj.getParcelableExtra("CustomerViewModel");

        /*I: create variables, note the image is from drawable so we dont need to get it*/
        final String customerName = customerViewModel.getName();
        final String customerTel = customerViewModel.getTelephone();
        /*I: note here you will have to add the orders number that will come from the obj*/

        final TextView nametextView = findViewById(R.id.adedcustname_input_id);
        final TextView telextView = findViewById(R.id.adedcusttel_input_id);
        TextView ordertextView = findViewById(R.id.adedcustorder_input_id);
        Button deleteCustomer = (Button)findViewById(R.id.adecustdelete_customer_id);

        nametextView.setText(customerName);
        telextView.setText(customerTel);
        ordertextView.setText("99");//this is hardcoded for now.

        deleteCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean result = dbH.DeleteCustomerByTelephoneNumber(customerTel);
                if (result){
                    Toast.makeText(AdminEditCustomerActivuty.this, "Customer ("+customerName+") was deleted!", Toast.LENGTH_SHORT).show();
                    Intent baseAdmin = new Intent(v.getContext(),MainAdminActivity.class);

                    finish();
                }
                else {
                    Toast.makeText(AdminEditCustomerActivuty.this, "Customer ("+customerName+") was NOT deleted!", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
