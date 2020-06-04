package com.example.orderquick;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class MainCustomerView extends BaseAppClass {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private TabItem pizza,pasta,desserts,vegetarian;
    public PagerAdapter pagerAdapter;
    /*I: Trolley-notification vars*/
    MenuItem menuItem;
    TextView trolleyBadgeCounter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_customer_view);

        //init
        tabLayout = (TabLayout)findViewById(R.id.customer_main_tabulator_id);
        pizza = (TabItem)findViewById(R.id.customer_main_pizza_item_id);
        pasta = (TabItem)findViewById(R.id.customer_main_pasta_item_id);
        desserts = (TabItem)findViewById(R.id.customer_main_desserts_item_id);
        vegetarian = (TabItem)findViewById(R.id.customer_main_vegetarian_item_id);
        viewPager = (ViewPager)findViewById(R.id.customer_main_viewpager_id);
        pagerAdapter = new PageAdapter(getSupportFragmentManager(),tabLayout.getTabCount());
        viewPager.setAdapter(pagerAdapter);

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                if(tab.getPosition()==0){pagerAdapter.notifyDataSetChanged();}
                else if(tab.getPosition()==1){pagerAdapter.notifyDataSetChanged();}
                else if(tab.getPosition()==2){pagerAdapter.notifyDataSetChanged();}
                else if(tab.getPosition()==3){pagerAdapter.notifyDataSetChanged();}
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
                viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
    }


    /*I: Function to inflate the customer menu for the user.*/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater i = getMenuInflater();
        //i.inflate(R.menu.custommer_menu,menu);
//        /*I: logic for the trolley-notification*/
//        menuItem = menu.findItem(R.id.trolley_icon_menu_id);
//        if (TROLLEY_NOTIFICATION==0){
//            /*I: since there is no notification, there is no need to show the badger*/
//            menuItem.setActionView(null);
//        }else {
//            /*I: set the action view*/
//            menuItem.setActionView(R.layout.trolly_notification_badge_layout);
//            /*I: set text view to show the notification account*/
//            View v = menuItem.getActionView();
//            trolleyBadgeCounter=v.findViewById(R.id.trolley_badge_counter);
//            trolleyBadgeCounter.setText(String.valueOf(TROLLEY_NOTIFICATION));
//        }

        /*I: note on how to inflate menu when there are both fragments and activity:
        *    - This method will serve the fragments - in the fragments the same method
        *      will be overwritten. Inside of the fragment the actual menu items are inflated
        *    - As a conclusion, if the menu will change at running time, a good approach is to
        *      inflate the menu from the fragment.
        * */
        return true;
    }

    /*I: Menu items on click listener*/
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.customer_all_orders_id :
                Intent orderList = new Intent(this,CustomerOrderListActivity.class);
                startActivity(orderList);
                return true;
            case R.id.customer_profile_id:
                Intent profile = new Intent(this,CustomerProfile.class);
                startActivity(profile);
                return true;
//            case R.id.customer_orders_id:
//                Toast.makeText(this, "Create and show the list of orders the user has if null show message there isnt any order yet", Toast.LENGTH_LONG).show();
//                return true;
            case R.id.customer_logout_id:
                //set the current user to null and redirect to login
                APPLICATION_CURRENT_USER =null;
                /*I: re-set the trolley notifications*/
                TROLLEY_NOTIFICATION=0;
                /*I: empty the list of orders*/
                ORDER_LIST.clear();
                Intent login = new Intent(this,LogIn.class);
                startActivity(login);
                //deny user to go back to previous activity once is logged out.
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
