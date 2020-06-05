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
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_customers, container, false);
        final DBHelper dbH = new DBHelper(container.getContext());
        //this list will hold the elements we want to insert ino the fragment
        //get the list of users,for each user create a customer view model and add it to the array list.

        final ArrayList<CustomerViewModel> customerViewModels =new ArrayList<>();
        ArrayList<CustomerModel> dbCustomers = dbH.GetAllCustomers();
        for (CustomerModel customer:dbCustomers) {
            CustomerViewModel cvm = new CustomerViewModel(R.drawable.user2,customer.getCustomerName(),customer.getTelephoneNumber());
            customerViewModels.add(cvm);
        }


        recyclerView = (RecyclerView)view.findViewById(R.id.admin_customer_recycler_view_id);
//        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(view.getContext());
        adapter = new AdminCustomerAdapter(customerViewModels);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new AdminCustomerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                /*I: get current item*/
                CustomerViewModel customerViewModel = customerViewModels.get(position);
                /*I: create new intent to the delete customer activity*/
                Intent editCustomer = new Intent(container.getContext(),AdminEditCustomerActivuty.class);
                /*I: send object to the new intent - NOTE YOU MIGHT LIKE TO SEND THE ID TO!*/
                editCustomer.putExtra("CustomerViewModel",customerViewModel);
                startActivity(editCustomer);
            }

            @Override
            public void onItemDelete(int position) {
                /*I: here handle user when clicking on the item*/
                CustomerViewModel customerViewModel = customerViewModels.get(position);
                boolean result = dbH.DeleteCustomerByTelephoneNumber(customerViewModel.getTelephone());
                if (result){
                    customerViewModels.remove(position);
                    adapter.notifyItemRemoved(position);
                    adapter.notifyItemRangeChanged(position,customerViewModels.size());
                    Toast.makeText(view.getContext(), "Customer ("+customerViewModel.getName()+") has been deleted", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(view.getContext(), "Customer ("+customerViewModel.getName()+") has NOT been deleted", Toast.LENGTH_SHORT).show();
                }
            }
        });
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_customers, container, false);
        return view;
    }

}
