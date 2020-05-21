package com.example.orderquick;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class PageAdapter extends FragmentPagerAdapter {

    private int tabNum;
    public PageAdapter(@NonNull FragmentManager fm, int itabNum) {
        super(fm);
        this.tabNum = itabNum;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        //define tabs
        switch (position){
            case 0:
                return new PizzaFragment();
            case 1:
                return new PastaFragment();
            case 2:
                return new DessertFragment();
            case 3:
                return new VegetarianFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabNum;
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return POSITION_NONE;
    }
}
