package com.example.orderquick;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.orderquick.BaseAppClass.ORDER_LIST;
import static com.example.orderquick.BaseAppClass.ORDER_TOTAL;
import static com.example.orderquick.BaseAppClass.TROLLEY_NOTIFICATION;

public class CustomerOrderListActivity extends AppCompatActivity {

    /*I: add necessary reference to us the recycler view.*/
    private RecyclerView mealRecyclerView;
    private AdminMealAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private TextView total;

    MenuItem menuItem;
    TextView trolleyBadgeCounter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_order_list);

        /*I: databases helper instance.*/
        final DBHelper dbH = new DBHelper(this);

        /*I: note, within this action the order list is initiated from
         the global var storing the order list*/
        mealRecyclerView = (RecyclerView)findViewById(R.id.order_list_recycler);
        total = (TextView)findViewById(R.id.order_list_sum);
        layoutManager = new LinearLayoutManager(this);
        adapter = new AdminMealAdapter(ORDER_LIST);
        mealRecyclerView.setLayoutManager(layoutManager);
        mealRecyclerView.setAdapter(adapter);

        ORDER_TOTAL=0;
        /*I: summarise the meals price.*/
        for (MealModel order:ORDER_LIST) {
            ORDER_TOTAL = ORDER_TOTAL+ Integer.parseInt( order.getMealPrice());
        }
        /*I: display order price*/
        total.setText("€ "+ ORDER_TOTAL);

        adapter.setOnItemClickListener(new AdminMealAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                /*I: code to open the meal activity you should pass the model to the intent*/
                MealModel mm = ORDER_LIST.get(position);
//                Toast.makeText(view.getContext(), mm.getMealName(), Toast.LENGTH_SHORT).show();
                /*I: besides rendering the user to a new activity,
                 * we are also passing the meal id by which the meal can be queried from SQLite*/
                Intent pIntent = new Intent(CustomerOrderListActivity.this,CustomerMealActivity.class);
                pIntent.putExtra("MEAL_ID",mm.getMealId());
                startActivity(pIntent);
            }

            @Override
            public void onDeleteClick(int position) {
                /*I: element at current position*/
                MealModel mm =ORDER_LIST.get(position);
                ORDER_TOTAL = ORDER_TOTAL-Integer.parseInt(mm.getMealPrice());
                TROLLEY_NOTIFICATION--;
                ORDER_LIST.remove(position);
                adapter.notifyItemRemoved(position);
                adapter.notifyItemRangeChanged(position,ORDER_LIST.size());
                total.setText("€ "+ ORDER_TOTAL);


                Toast.makeText(CustomerOrderListActivity.this, "Delete stuff ("+mm.getMealName()+"", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
