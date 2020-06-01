package com.example.orderquick;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static com.example.orderquick.BaseAppClass.ORDER_LIST;
import static com.example.orderquick.BaseAppClass.TROLLEY_NOTIFICATION;


/**
 * A simple {@link Fragment} subclass.
 */
public class DessertFragment extends Fragment {


    private RecyclerView mealRecyclerView;
    private UniversalCustomerFragmentsAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    MenuItem menuItem;
    TextView trolleyBadgeCounter;

    public DessertFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_dessert, container, false);
        final DBHelper dbH = new DBHelper(container.getContext());
        /*I: this is necessary to be able to alter menu items from the fragments*/
        setHasOptionsMenu(true);

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
                /*I: when the user adds an element to the order list,
                 *    the notifications increments by one the screen.*/
                TROLLEY_NOTIFICATION++;
                menuItem.setActionView(R.layout.trolly_notification_badge_layout);

                /*I: set text view to show the notification account*/
                View vv = menuItem.getActionView();
                trolleyBadgeCounter=vv.findViewById(R.id.trolley_badge_counter);
                trolleyBadgeCounter.setText(String.valueOf(TROLLEY_NOTIFICATION));
                /*I: end of the notification section*/

                /*I: add the meal to the ArrayList that holds the meals for a particular order.*/
                ORDER_LIST.add(mm);
                /*I: these logs are for developing purposes only, user has no access to them.*/
                Log.i("Added Meal -->",mm.getMealName());
                Log.i("Order List size -->",String.valueOf( ORDER_LIST.size()));
                Toast.makeText(view.getContext(), mm.getMealName()+" ORDERED!", Toast.LENGTH_SHORT).show();

            }
        });

        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.custommer_menu,menu);
        menuItem = menu.findItem(R.id.trolley_icon_menu_id);
        if (TROLLEY_NOTIFICATION==0){
            /*I: since there is no notification, there is no need to show the badger*/
            menuItem.setActionView(null);
        }else {
            /*I: set the action view*/
            menuItem.setActionView(R.layout.trolly_notification_badge_layout);
            /*I: set text view to show the notification account*/
            View v = menuItem.getActionView();
            trolleyBadgeCounter=v.findViewById(R.id.trolley_badge_counter);
            trolleyBadgeCounter.setText(String.valueOf(TROLLEY_NOTIFICATION));
        }
        super.onCreateOptionsMenu(menu, inflater);
    }
}
