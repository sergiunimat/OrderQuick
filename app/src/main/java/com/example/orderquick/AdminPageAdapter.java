package com.example.orderquick;

import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class AdminPageAdapter extends FragmentPagerAdapter {
    private int taskNr;

    public AdminPageAdapter(@NonNull FragmentManager fm,int itaskNr) {
        super(fm);
        taskNr=itaskNr;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch(position){
            case 0:
                return new MealsFragment();
            case 1:
                return new CustomersFragment();
            case 2:
                return new EmployeesFragment();
            default:
                return null;
        }


    }

    @Override
    public int getCount() {
        return taskNr;
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return POSITION_NONE;
    }
}
