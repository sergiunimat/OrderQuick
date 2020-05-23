package com.example.orderquick;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class MainAdminActivity extends BaseAppClass {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private TabItem meal,order,customer,employee;
    public AdminPageAdapter pageAdapter;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.admin_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.admin_profile_id:
                Intent profile = new Intent(this,CustomerProfile.class);
                startActivity(profile);
                return true;
            case R.id.admin_ad_employee_id:
                Intent addEmp = new Intent(this,AdminAddEmployee.class);
                startActivity(addEmp);
                return true;
            case R.id.admin_ad_meal_id:
                Toast.makeText(this, "Add meal - This mifgt be the most challenging one ", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.admin_logout_id:
                APPLICATION_CURRENT_USER =null;
                Intent login = new Intent(this,LogIn.class);
                startActivity(login);
                //deny user to go back to previous activity once is logged out.
                finish();

                return true;
                default:
                    return super.onOptionsItemSelected(item);
        }
    }

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
