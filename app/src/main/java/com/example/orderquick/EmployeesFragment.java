package com.example.orderquick;


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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_employees, container, false);
        DBHelper dbH = new DBHelper(container.getContext());


        final ArrayList<EmployeeViewModel>employeeViewModels = new ArrayList<>();
        employeeViewModels.add(new EmployeeViewModel(R.drawable.user,1,"Sergiu","7777777","1000",R.drawable.delete));
        employeeViewModels.add(new EmployeeViewModel(R.drawable.user,12,"Graziella","7777777","1000",R.drawable.delete));
        employeeViewModels.add(new EmployeeViewModel(R.drawable.user,13,"Ricco","7777777","1000",R.drawable.delete));
        employeeViewModels.add(new EmployeeViewModel(R.drawable.user,14,"Mango","7777777","1000",R.drawable.delete));


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
            }

            @Override
            public void onDeleteClick(int position) {
                /*I: here we handle the direct delete button click*/
                EmployeeViewModel employeeViewModel = employeeViewModels.get(position);
                Toast.makeText(view.getContext(),"DELETE ("+employeeViewModel.getEmpName()+") !" , Toast.LENGTH_SHORT).show();
            }
        });
        // Inflate the layout for this fragment
        return view;
    }

}
