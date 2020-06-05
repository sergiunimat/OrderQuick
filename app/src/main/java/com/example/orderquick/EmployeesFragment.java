package com.example.orderquick;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class EmployeesFragment extends Fragment {


    public EmployeesFragment() {
        // Required empty public constructor
    }

    private RecyclerView empRecycleView;
    private AdminEmployeAdapter empAdapter;
    private RecyclerView.LayoutManager empManager;


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_employees, container, false);
        DBHelper dbH = new DBHelper(container.getContext());


        final ArrayList<EmployeeViewModel>employeeViewModels = new ArrayList<>();
        ArrayList<CustomerModel> dbEmployees = dbH.GetAllEmployees();
        for (CustomerModel employee:dbEmployees) {
            EmployeeViewModel evm = new EmployeeViewModel(R.drawable.user3,employee.getCustomerId(),employee.getCustomerName(),employee.getTelephoneNumber(),employee.getWage(),R.drawable.delete);
            employeeViewModels.add(evm);
        }


        empRecycleView = (RecyclerView) view.findViewById(R.id.employee_customer_recycler_view_id);
        empManager = new LinearLayoutManager(view.getContext());
        empAdapter = new AdminEmployeAdapter(employeeViewModels);

        empRecycleView.setLayoutManager(empManager);
        empRecycleView.setAdapter(empAdapter);

        empAdapter.setOnItemClickListener(new AdminEmployeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                /*I: here each click on each item is handled*/
                EmployeeViewModel employeeViewModel = employeeViewModels.get(position);
                Toast.makeText(view.getContext(),employeeViewModel.getEmpName() , Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(container.getContext(),AdminEditDeleteEmployee.class);
                intent.putExtra("EmployeeViewModel",employeeViewModel);
                startActivity(intent);
            }

            @Override
            public void onDeleteClick(int position) {
                /*I: here we handle the direct delete button click*/
                EmployeeViewModel employeeViewModel = employeeViewModels.get(position);
                DBHelper dbH = new DBHelper(container.getContext());
                boolean result = dbH.DeleteCustomerById(employeeViewModel.getEmpId());
                if (result){
                    employeeViewModels.remove(position);
                    empAdapter.notifyItemRemoved(position);
                    empAdapter.notifyItemRangeChanged(position,employeeViewModels.size());
                    Toast.makeText(view.getContext(),"Employee ("+employeeViewModel.getEmpName()+") is deleted !" , Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(view.getContext(), "Employee could not be deleted", Toast.LENGTH_SHORT).show();
                }
                
            }
        });
        // Inflate the layout for this fragment
        return view;
    }

}
