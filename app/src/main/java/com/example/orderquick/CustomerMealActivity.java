package com.example.orderquick;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class CustomerMealActivity extends BaseAppClass {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_meal);
        DBHelper dbH = new DBHelper(this);
        /*I: getting the meal id (of type int) from where its been sent (i.e. customer fragments)*/
        Intent mIntent = getIntent();
        int mId = mIntent.getIntExtra("MEAL_ID", 0);

        /*I: init the meal model based on the passed it.
        * NOTE, there was an attempt to pass the entire Meal object however,
        * the object contains the Bitmap which is a large property.
        * it took me several hours to understand that large objects CAN NOT be
        * transferred from one activity/fragment to another.*/
        MealModel mealModel = dbH.GetMealById(mId);

        /*I: init the layout of the activity*/
        ImageView mImg = (ImageView)findViewById(R.id.customer_meal_id);
        TextView mName = (TextView)findViewById(R.id.customer_meal_name);
        TextView mCategory = (TextView)findViewById(R.id.customer_meal_category);
        TextView mDescription = (TextView)findViewById(R.id.customer_meal_description);
        TextView mPrice = (TextView)findViewById(R.id.customer_meal_price);
        Button mAddToOrderList = (Button)findViewById(R.id.add_meal_order);

        mImg.setImageBitmap(mealModel.getMealImg());
        mName.setText(mealModel.getMealName());
        mCategory.setText(mealModel.getMealCategory());
        mDescription.setText(mealModel.getMealDescription());
        mPrice.setText(mealModel.getMealPrice());

        mAddToOrderList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*I: add the item to the order array */
                Toast.makeText(CustomerMealActivity.this, "added to order", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
