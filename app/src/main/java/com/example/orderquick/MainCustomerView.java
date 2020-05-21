package com.example.orderquick;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class MainCustomerView extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private TabItem pizza,pasta,desserts,vegetarian;
    public PagerAdapter pagerAdapter;

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
}
