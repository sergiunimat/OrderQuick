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
public class CustomersFragment extends Fragment {

    private RecyclerView recyclerView;
    /*I: the adapter is the bridge between the data and the recycler view above
    *    We need to create our own adapter.*/
    private AdminCustomerAdapter adapter;
    /*I: this is responsible for aligning single items in the list*/
    private RecyclerView.LayoutManager layoutManager;


    public CustomersFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_customers, container, false);
        DBHelper dbH = new DBHelper(container.getContext());
        //this list will hold the elements we want to insert ino the fragment
        //get the list of users,for each user create a customer view model and add it to the array list.

        final ArrayList<CustomerViewModel> customerViewModels =new ArrayList<>();
        ArrayList<CustomerModel> dbCustomers = dbH.GetAllCustomers();
        for (CustomerModel customer:dbCustomers) {
            CustomerViewModel cvm = new CustomerViewModel(R.drawable.user2,customer.getCustomerName(),customer.getTelephoneNumber());
            customerViewModels.add(cvm);
        }
//        customerViewModels.add(new CustomerViewModel(R.drawable.user2,"Sergiu","7777777"));
//        customerViewModels.add(new CustomerViewModel(R.drawable.user2,"Graziella","1111111"));
//        customerViewModels.add(new CustomerViewModel(R.drawable.user2,"Ricco","5555555"));
//        customerViewModels.add(new CustomerViewModel(R.drawable.user2,"Mango","6666666"));
//        customerViewModels.add(new CustomerViewModel(R.drawable.user2,"Sergiu","7777777"));
//        customerViewModels.add(new CustomerViewModel(R.drawable.user2,"Graziella","1111111"));
//        customerViewModels.add(new CustomerViewModel(R.drawable.user2,"Ricco","5555555"));
//        customerViewModels.add(new CustomerViewModel(R.drawable.user2,"Mango","6666666"));
//        customerViewModels.add(new CustomerViewModel(R.drawable.user2,"Sergiu","7777777"));
//        customerViewModels.add(new CustomerViewModel(R.drawable.user2,"Graziella","1111111"));
//        customerViewModels.add(new CustomerViewModel(R.drawable.user2,"Ricco","5555555"));
//        customerViewModels.add(new CustomerViewModel(R.drawable.user2,"Mango","6666666"));
//        customerViewModels.add(new CustomerViewModel(R.drawable.user2,"Sergiu","7777777"));
//        customerViewModels.add(new CustomerViewModel(R.drawable.user2,"Graziella","1111111"));
//        customerViewModels.add(new CustomerViewModel(R.drawable.user2,"Ricco","5555555"));
//        customerViewModels.add(new CustomerViewModel(R.drawable.user2,"Mango","6666666"));
//        customerViewModels.add(new CustomerViewModel(R.drawable.user2,"Sergiu","7777777"));
//        customerViewModels.add(new CustomerViewModel(R.drawable.user2,"Graziella","1111111"));
//        customerViewModels.add(new CustomerViewModel(R.drawable.user2,"Ricco","5555555"));
//        customerViewModels.add(new CustomerViewModel(R.drawable.user2,"Mango","6666666"));

        recyclerView = (RecyclerView)view.findViewById(R.id.admin_customer_recycler_view_id);
//        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(view.getContext());
        adapter = new AdminCustomerAdapter(customerViewModels);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new AdminCustomerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                CustomerViewModel customerViewModel = customerViewModels.get(position);

                //here we take the element and pass it to the new customer activity activity
                Toast.makeText(view.getContext(), customerViewModel.getName() , Toast.LENGTH_SHORT).show();
            }
        });
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_customers, container, false);
        return view;
    }

}
