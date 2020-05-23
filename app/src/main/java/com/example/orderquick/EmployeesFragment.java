package com.example.orderquick;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class EmployeesFragment extends Fragment {


    public EmployeesFragment() {
        // Required empty public constructor
    }

    private RecyclerView empRecycleView;
    private RecyclerView.Adapter empAdapter;
    private RecyclerView.LayoutManager empManager;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_employees, container, false);
        DBHelper dbH = new DBHelper(container.getContext());


        ArrayList<EmployeeViewModel>employeeViewModels = new ArrayList<>();
        employeeViewModels.add(new EmployeeViewModel(R.drawable.user,1,"Sergiu","7777777","1000"));
        employeeViewModels.add(new EmployeeViewModel(R.drawable.user,12,"Graziella","7777777","1000"));
        employeeViewModels.add(new EmployeeViewModel(R.drawable.user,13,"Ricco","7777777","1000"));
        employeeViewModels.add(new EmployeeViewModel(R.drawable.user,14,"Mango","7777777","1000"));


        empRecycleView = (RecyclerView) view.findViewById(R.id.employee_customer_recycler_view_id);
        empManager = new LinearLayoutManager(view.getContext());
        empAdapter = new AdminEmployeAdapter(employeeViewModels);

        empRecycleView.setLayoutManager(empManager);
        empRecycleView.setAdapter(empAdapter);
        // Inflate the layout for this fragment
        return view;
    }

}
