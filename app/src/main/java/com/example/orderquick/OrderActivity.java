package com.example.orderquick;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.example.orderquick.BaseAppClass.ORDER_LIST;

public class OrderActivity extends AppCompatActivity {
    private TextView oId;
    private TextView oCname;
    private TextView oTotPrice;

    /*I: add necessary reference to us the recycler view.*/
    private RecyclerView mealRecyclerView;
    private AdminMealAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private TextView total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        /*I: reference to the database helper*/
        DBHelper dbH = new DBHelper(this);

        /*I: get the order object*/
        Intent inte = getIntent();
        OrderModel orderModel = inte.getParcelableExtra("OrderObj");
        /*I: set the customer name who created the order.*/
        CustomerModel tempCus = dbH.GetCustomerById(orderModel.getCustomerId());
        /*I: get the total amount of price - at the same time we get all the meals from database
        * all the meals related to the order.*/
        ArrayList<MealModel> listOfMeals = new ArrayList<>();
        Double totalAmount = 0.0;
        String[] elements = orderModel.getOrderList().split(",");
        for (String id :elements) {

            MealModel tempMel = dbH.GetMealById(Integer.parseInt(id));
            /*I: add the meal to the list*/
            listOfMeals.add(tempMel);
            /*I: increase the total amount of money to get the total sum of the order*/
            totalAmount=totalAmount+Double.parseDouble(tempMel.getMealPrice());
        }


        /*I: create new variables and assign values to them.
         * I: Super important that we need to convert variables otherwise JAVA will throw us
         *    the most unpleasant errors :)*/
        /*I: set the order id*/
        String orderId = String.valueOf(orderModel.getCustomerId());
        String customerName = String.valueOf(tempCus.getCustomerName());
        String totalPrice = String.valueOf(totalAmount);

        mealRecyclerView = (RecyclerView)findViewById(R.id.orderrecyclerview);
        oId=findViewById(R.id.orderidtext);
        oCname = findViewById(R.id.ordercustomername);
        oTotPrice =findViewById(R.id.orderprice);
        /*WE ALSO HAVE THE LIST*/

        try {
            oId.setText(orderId);
            oCname.setText(customerName);
            oTotPrice.setText("€: "+totalPrice);
            layoutManager = new LinearLayoutManager(this);
            adapter = new AdminMealAdapter(listOfMeals);
            mealRecyclerView.setLayoutManager(layoutManager);
            mealRecyclerView.setAdapter(adapter);

        }catch (Exception e){
            Log.i("ERROR-->",e.toString());
        }


    }
}
