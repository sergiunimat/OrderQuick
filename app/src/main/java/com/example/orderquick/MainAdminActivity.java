package com.example.orderquick;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class MainAdminActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private TabItem meal,order,customer,employee;
    public AdminPageAdapter pageAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_admin);

        /*init*/
        tabLayout= (TabLayout)findViewById(R.id.admin_tab_layout_id);
        meal = (TabItem)findViewById(R.id.admin_meal_id);
        order = (TabItem)findViewById(R.id.admin_order_id);
        customer = (TabItem)findViewById(R.id.admin_customer_id);
        employee = (TabItem)findViewById(R.id.admin_employee_id);
        viewPager =(ViewPager)findViewById(R.id.admin_view_pager_id);

        pageAdapter=new AdminPageAdapter(getSupportFragmentManager(),tabLayout.getTabCount());
        viewPager.setAdapter(pageAdapter);

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                if(tab.getPosition()==0){pageAdapter.notifyDataSetChanged();}
                else if(tab.getPosition()==1){pageAdapter.notifyDataSetChanged();}
                else if(tab.getPosition()==2){pageAdapter.notifyDataSetChanged();}
                else if(tab.getPosition()==3){pageAdapter.notifyDataSetChanged();}
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
