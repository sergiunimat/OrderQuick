package com.example.orderquick;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class CustomerMealActivity extends BaseAppClass {

    MenuItem menuItem;
    TextView trolleyBadgeCounter;

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
        final MealModel mealModel = dbH.GetMealById(mId);

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
                    ORDER_LIST.add(mealModel);
                    /*I: these logs are for developing purposes only, user has no access to them.*/
                    Log.i("Added Meal -->",mealModel.getMealName());
                    Log.i("Order List size -->",String.valueOf( ORDER_LIST.size()));

                    /*I: after the user adds the meal to the list he/she is rendered back to customer main activity*/
                    Intent mAc = new Intent(CustomerMealActivity.this,MainCustomerView.class);
                    startActivity(mAc);
                    /*I: note, the user is allows to go back to this view by using back arrow.*/

                Toast.makeText(CustomerMealActivity.this, "added to order", Toast.LENGTH_SHORT).show();
            }
        });

    }

    /*I: this method is responsible to render the menu on the app-header.
    *    Furthermore, here the are the base setups for the trolley notification (i.e. when there are meals in order.)*/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.custommer_menu,menu);
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
        return super.onCreateOptionsMenu(menu);
    }
}
