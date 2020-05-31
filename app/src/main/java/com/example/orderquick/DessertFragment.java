package com.example.orderquick;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
public class DessertFragment extends Fragment {


    private RecyclerView mealRecyclerView;
    private UniversalCustomerFragmentsAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    public DessertFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_dessert, container, false);
        final DBHelper dbH = new DBHelper(container.getContext());

        ArrayList<MealModel> mealModels = new ArrayList<>();
        Bitmap icon = BitmapFactory.decodeResource(container.getResources(),
                R.drawable.burger);

        ArrayList<MealModel> listofmm = new ArrayList<>();
        listofmm=dbH.GetAllDesertMeals();

        mealRecyclerView = (RecyclerView)view.findViewById(R.id.dessert_meal_recycler_view_id);
        layoutManager = new LinearLayoutManager(view.getContext());
        adapter = new UniversalCustomerFragmentsAdapter(listofmm);
        mealRecyclerView.setLayoutManager(layoutManager);
        mealRecyclerView.setAdapter(adapter);

        final ArrayList<MealModel> finalListofmm = listofmm;
        adapter.setOnItemClickListener(new UniversalCustomerFragmentsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                /*I: code to open the meal activity you should pass the model to the intent*/
                MealModel mm = finalListofmm.get(position);
                Toast.makeText(view.getContext(), mm.getMealName(), Toast.LENGTH_SHORT).show();

                /*I: transfer the meal id to the activity*/
                Intent pIntent = new Intent(container.getContext(),CustomerMealActivity.class);
                pIntent.putExtra("MEAL_ID",mm.getMealId());
                startActivity(pIntent);
            }

            @Override
            public void onItemAddOrder(int position) {
                /*I: code to order meal directly*/
                MealModel mm = finalListofmm.get(position);
                Toast.makeText(view.getContext(), mm.getMealName()+" ORDERED!", Toast.LENGTH_SHORT).show();

            }
        });

        return view;
    }

}
