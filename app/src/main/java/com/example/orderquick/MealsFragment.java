package com.example.orderquick;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class MealsFragment extends Fragment {

    private RecyclerView mealRecyclerView;
    private AdminMealAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    public MealsFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_meals, container, false);
        final DBHelper dbH = new DBHelper(container.getContext());

        /*
        * NOTE, you need to be able to retrieve a MealModel object from the database
        * - that means you need to decode the byte array into the database function
        * - next create a Bitmap and then pass it to the new object.
        * */


            ArrayList<MealModel> listofmm = new ArrayList<>();
            listofmm=dbH.GetAllMeals();



            mealRecyclerView = (RecyclerView)view.findViewById(R.id.meal_recycler_view_id);
            layoutManager = new LinearLayoutManager(view.getContext());
            adapter = new AdminMealAdapter(listofmm);
            mealRecyclerView.setLayoutManager(layoutManager);
            mealRecyclerView.setAdapter(adapter);

        final ArrayList<MealModel> finalListofmm = listofmm;
        adapter.setOnItemClickListener(new AdminMealAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(int position) {
                    /*I: handle click on item*/
                    MealModel mm =finalListofmm.get(position);
                    Toast.makeText(container.getContext(), mm.getMealName(), Toast.LENGTH_SHORT).show();
                    /*I: besides rendering the user to a new activity,
                     * we are also passing the meal id by which the meal can be queried from SQLite*/
                    Intent pIntent = new Intent(container.getContext(),AdminMealActivity.class);
                    pIntent.putExtra("MEAL_ID",mm.getMealId());
                    startActivity(pIntent);
                }

            @Override
            public void onDeleteClick(int position) {
                /*I: here handle delete meal directly*/
                /*I: get current item then delete it by id.*/
                MealModel mm =finalListofmm.get(position);
                boolean result = dbH.DeleteMealById(mm.getMealId());
                if (result){
                    Toast.makeText(view.getContext(), "The ("+mm.getMealName()+") successfully deleted ", Toast.LENGTH_SHORT).show();
                    adapter.notifyItemRemoved(position);
                }
                else {
                    Toast.makeText(view.getContext(), "The ("+mm.getMealName()+") was not deleted ", Toast.LENGTH_SHORT).show();
                }
            }
        });




        // Inflate the layout for this fragment
        return view;
    }

}
