package com.example.orderquick;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class AdminEditDeleteEmployee extends BaseAppClass {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_edit_delete_employee);
        final DBHelper dbH = new DBHelper(this);
        Intent employeobj=getIntent();
        final EmployeeViewModel employeeViewModel = employeobj.getParcelableExtra("EmployeeViewModel");

        int empId = employeeViewModel.getEmpId();
        String empName = employeeViewModel.getEmpName();
        String empTel = employeeViewModel.getEmpTel();
        String empWage = employeeViewModel.getEmWage();

        String semId = Integer.toString(empId);

        final TextView eId = findViewById(R.id.edit_employeid_id);
        final TextView eName = findViewById(R.id.edit_employeename_id);
        final TextView eTel = findViewById(R.id.edit_employeetel_id);
        final TextView eWage= findViewById(R.id.edit_employeepassword_id);
        Button eEdit = (Button)findViewById(R.id.edit_employee_Btn);
        Button eDelete = (Button)findViewById(R.id.del_employee_Btn);

        try {
            eId.setText(semId);
            eName.setText(empName);
            eTel.setText(empTel);
            eWage.setText(empWage);
        }
        catch (Exception e){
            Log.i("info",e.toString());
        }


        /*I: edit employee*/
        eEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*I: create new customer model*/
                CustomerModel dbuser = dbH.GetCustomerById(employeeViewModel.getEmpId());
                CustomerModel editEmp = new CustomerModel();
                editEmp.setCustomerId(employeeViewModel.getEmpId());
                editEmp.setCustomerName(eName.getText().toString());
                editEmp.setTelephoneNumber(eTel.getText().toString());
                editEmp.setWage(eWage.getText().toString());
                editEmp.setPassword(dbuser.getPassword());
                editEmp.setRole(dbuser.getRole());

                if (!(TextUtils.isEmpty(eName.getText().toString())
                        ||TextUtils.isEmpty(eTel.getText().toString())
                        ||TextUtils.isEmpty(eWage.getText().toString())))
                {
                    boolean result = dbH.EditCustomer(editEmp);
                    if (result){
                        Toast.makeText(AdminEditDeleteEmployee.this, "Employee edited", Toast.LENGTH_SHORT).show();
                        Intent intent  = new Intent(v.getContext(),MainAdminActivity.class);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(AdminEditDeleteEmployee.this, "Employee could not be edited", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(AdminEditDeleteEmployee.this, "Note, all the field must be filled in ", Toast.LENGTH_SHORT).show();
                }



            }
        });

        /*I: delete employee*/
        eDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*create fn if there isnt and delete user*/
                if (!(TextUtils.isEmpty(eName.getText().toString())
                        ||TextUtils.isEmpty(eTel.getText().toString())
                        ||TextUtils.isEmpty(eWage.getText().toString())))
                {
                    boolean result = dbH.DeleteCustomerById(employeeViewModel.getEmpId());
                    if(result){
                        Toast.makeText(AdminEditDeleteEmployee.this, "Employee ("+eName.getText()+") has been deleted", Toast.LENGTH_SHORT).show();
                        Intent back = new Intent(v.getContext(),MainAdminActivity.class);
                        startActivity(back);
                        finish();
                    }
                    else{
                        Toast.makeText(AdminEditDeleteEmployee.this, "Customer could not be deleted", Toast.LENGTH_SHORT).show();
                    }

                }
                else {
                    Toast.makeText(AdminEditDeleteEmployee.this, "Note, all the fields must be filled in", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}
