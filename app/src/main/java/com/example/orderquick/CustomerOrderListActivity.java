package com.example.orderquick;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import static com.example.orderquick.BaseAppClass.ORDER_LIST;
import static com.example.orderquick.BaseAppClass.ORDER_TOTAL;

public class CustomerOrderListActivity extends AppCompatActivity {

    /*I: add necessary reference to us the recycler view.*/
    private RecyclerView mealRecyclerView;
    private AdminMealAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private TextView total;

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
        /*I: summarise the meals price.*/
        for (MealModel order:ORDER_LIST) {
            ORDER_TOTAL = ORDER_TOTAL+ Integer.parseInt( order.getMealPrice());
        }
        /*I: display order price*/
        total.setText("€ "+String.valueOf(ORDER_TOTAL));


    }
}
