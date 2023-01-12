package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;

public class VPAdapter extends FragmentStateAdapter {

    private final ArrayList<Fragment> fragmentArrayList = new ArrayList<>();
    private final ArrayList<String> fragmentTitle =new ArrayList<>();

    public VPAdapter(Fragment fm){
        super(fm);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return fragmentArrayList.get(position);
    }

    @Override
    public int getItemCount() {
        return fragmentArrayList.size();
    }

    public void addFragment(Fragment fragment, String title){
        fragmentArrayList.add(fragment);
        fragmentTitle.add(title);
    }




    /*private final ArrayList<Fragment> fragmentArrayList = new ArrayList<>();
    private final ArrayList<String> fragmentTitle =new ArrayList<>();

    public VPAdapter(@NonNull FragmentManager fm,@FragmentPagerAdapter.Behavior int behavior){
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragmentArrayList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentArrayList.size();
    }

    public void addFragment(Fragment fragment, String title){
        fragmentArrayList.add(fragment);
        fragmentTitle.add(title);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position){
        return fragmentTitle.get(position);
    }*/
}