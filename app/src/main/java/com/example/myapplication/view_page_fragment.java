package com.example.myapplication;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.google.android.material.tabs.TabLayout;

public class view_page_fragment extends Fragment implements TabLayout.OnTabSelectedListener {

    private ViewPager2 viewPager2;
    private static final int NUM_PAGES = 4;
    private FragmentStateAdapter pagerAdapter;
    LinearLayout linearLayout;
    private TabLayout tabLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_view_page_fragment, container, false);

        viewPager2 = view.findViewById(R.id.pager);
        linearLayout = view.findViewById(R.id.list);
        pagerAdapter = new view_page_fragment.ScreenSlidePageAdapter(this);
        viewPager2.setAdapter(pagerAdapter);

        //Настройка tablayoutbar

        tabLayout = view.findViewById(R.id.tabLayouts);
        tabLayout.addTab(tabLayout.newTab().setText("T_1").setIcon(R.drawable.ic_baseline_trending_down));
        tabLayout.addTab(tabLayout.newTab().setText("T_2").setIcon(R.drawable.ic_baseline_waves));
        tabLayout.addTab(tabLayout.newTab().setText("T_3").setIcon(R.drawable.ic_baseline_stream));
        tabLayout.addTab(tabLayout.newTab().setText("T_4").setIcon(R.drawable.ic_baseline_star));
        tabLayout.addOnTabSelectedListener((TabLayout.OnTabSelectedListener) this);

       // tabLayout.setVisibility(View.GONE);

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                tabLayout.selectTab(tabLayout.getTabAt(position));
            }
        });

        return view;
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {

        viewPager2.setCurrentItem(tab.getPosition());
        tab.getIcon().setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_IN);

    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {
        tab.getIcon().setColorFilter(Color.parseColor("#a8a8a8"), PorterDuff.Mode.SRC_IN);
    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {
        tabLayout.setTabTextColors(Color.parseColor("#000000"),Color.parseColor("#FFFFFF"));
        tab.getIcon().setColorFilter(Color.parseColor("#a8a8a8"), PorterDuff.Mode.SRC_IN);

    }


    public class ScreenSlidePageAdapter extends FragmentStateAdapter {
        public ScreenSlidePageAdapter(view_page_fragment view_page_fragment) {
            super(view_page_fragment);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            switch (position) {
                case 0:
                    return new F_electromagn();
                case 1:
                    return new F_tepl();
                case 2:
                    return new F_vent();
                case 3:

                    return new F_energo();
                default:
                    return null;
            }
        }

        @Override
        public int getItemCount() {
            return NUM_PAGES;
        }
    }


}