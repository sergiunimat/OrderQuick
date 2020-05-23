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

public class AdminAddEmployee extends BaseAppClass {

    EditText empName;
    EditText empTel;
    EditText empWage;
    Button addEmployee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_employee);

        final DBHelper dbH = new DBHelper(this);

        empName = (EditText)findViewById(R.id.edUserRegName_addemployee);
        empTel = (EditText)findViewById(R.id.edUserRegTell_addemployee);
        empWage = (EditText)findViewById(R.id.edUserWage_addemployee);
        addEmployee = (Button) findViewById(R.id.registerBtn_addemployee);

        addEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*I: check if all fields are filled in */
                CustomerModel customerModel;
                try {


                    if (TextUtils.isEmpty(empName.getText().toString()) ||
                            TextUtils.isEmpty(empTel.getText().toString()) ||
                            TextUtils.isEmpty(empWage.getText().toString())) {
                        Toast.makeText(AdminAddEmployee.this, "Note, all the fields must be filled in ", Toast.LENGTH_SHORT).show();
                    } else {
                        /*I: check if username with same tel number already exists*/
                        customerModel = new CustomerModel(-1, empName.getText().toString(),
                                "employee",
                                empTel.getText().toString(),
                                2,
                                empWage.getText().toString());


                        boolean userTelExists = dbH.UniqueUserTel(empTel.getText().toString());
                        if (userTelExists) {
                            Toast.makeText(AdminAddEmployee.this, "This telephone number already exists", Toast.LENGTH_LONG).show();
                            empTel.setSelection(0);
                        } else {
                            boolean check = dbH.AddCustomerToDb(customerModel);
                            if (check) {
                                Toast.makeText(AdminAddEmployee.this, "Employee added to database", Toast.LENGTH_SHORT).show();
                                Intent back = new Intent(v.getContext(),MainAdminActivity.class);
                                startActivity(back);
                            } else {
                                Toast.makeText(AdminAddEmployee.this, "error while adding employee to database.", Toast.LENGTH_SHORT).show();
                            }
                        }

                    }
                }catch (Exception e){
                    Log.i("Info",e.toString());
                }
            }
        });

    }
}
