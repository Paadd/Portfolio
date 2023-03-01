package com.msa.cityfy.Adapters;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerWeatherAdapter extends FragmentPagerAdapter {

private final List<Fragment> fragmentList = new ArrayList<>();
private final List<String> fragmentTitle= new ArrayList<>();

    public ViewPagerWeatherAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }


    @NonNull
    @Override
    public Fragment getItem(int i) {
        return fragmentList.get(i);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    public void addFragment( Fragment fragment, String title)
    {
        fragmentList.add(fragment);
        fragmentTitle.add(title);
    }

    @NonNull
    @Override

    public CharSequence getPageTitle(int position){
        return fragmentTitle.get(position);
    }


}
