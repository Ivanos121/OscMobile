package com.example.myapplication;

import android.annotation.SuppressLint;
import android.graphics.BlendMode;
import android.graphics.BlendModeColorFilter;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;

import java.util.Objects;

public class view_page_fragment extends Fragment implements TabLayout.OnTabSelectedListener {

    private ViewPager2 viewPager2;
    private static final int NUM_PAGES = 5;
    private FragmentStateAdapter pagerAdapter;
    LinearLayout linearLayout;
    private TabLayout tabLayout;
    private final int numItems = 4;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_view_page_fragment, container, false);

        viewPager2 = view.findViewById(R.id.pager);
        viewPager2.setPageTransformer(new DepthPageTransformer());
        linearLayout = view.findViewById(R.id.list);
        pagerAdapter = new view_page_fragment.ScreenSlidePageAdapter(this);
        viewPager2.setAdapter(pagerAdapter);

        //Настройка tablayoutbar

        tabLayout = view.findViewById(R.id.tabLayouts);
        tabLayout.addTab(tabLayout.newTab().setText("T_1").setIcon(R.drawable.ic_baseline_trending_down));
        tabLayout.addTab(tabLayout.newTab().setText("T_2").setIcon(R.drawable.ic_baseline_waves));
        tabLayout.addTab(tabLayout.newTab().setText("T_3").setIcon(R.drawable.ic_baseline_stream));
        tabLayout.addTab(tabLayout.newTab().setText("T_4").setIcon(R.drawable.ic_baseline_star));
        tabLayout.addTab(tabLayout.newTab().setText("T_41").setIcon(R.drawable.ic_baseline_star));
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

        switch (tab.getPosition()) {
            case 0:
                ((TextView) requireActivity().findViewById(R.id.textTitle)).setText("Подключение");
                break;
            case 1:
                ((TextView) requireActivity().findViewById(R.id.textTitle)).setText("Тепло");
                break;
            case 2:
                ((TextView) requireActivity().findViewById(R.id.textTitle)).setText("Вентиляция");
                break;
            case 3:
                ((TextView) requireActivity().findViewById(R.id.textTitle)).setText("Энергетика");
                break;
            case 4:
                ((TextView) requireActivity().findViewById(R.id.textTitle)).setText("Энергетическая диаграмма");
        }

        Objects.requireNonNull(tab.getIcon()).setColorFilter(new BlendModeColorFilter(ResourcesCompat.getColor(getResources(),R.color.colorGreen,null), BlendMode.SRC_IN));
        // tab.getIcon().setColorFilter(Color.parseColor("#32CD32"), PorterDuff.Mode.SRC_IN);



    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {
       // Objects.requireNonNull(tab.getIcon()).setColorFilter(Color.parseColor("#000000"), PorterDuff.Mode.SRC_IN);
        Objects.requireNonNull(tab.getIcon()).setColorFilter(new BlendModeColorFilter(ResourcesCompat.getColor(getResources(),R.color.colorBlack,null), BlendMode.SRC_IN));


    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {
        tabLayout.setTabTextColors(Color.parseColor("#000000"),Color.parseColor("#FFFFFF"));
        //tab.getIcon().setColorFilter(Color.parseColor("#a8a8a8"), PorterDuff.Mode.SRC_IN);
        Objects.requireNonNull(tab.getIcon()).setColorFilter(new BlendModeColorFilter(ResourcesCompat.getColor(getResources(),R.color.colorGreen,null), BlendMode.SRC_IN));


    }


    public static class ScreenSlidePageAdapter extends FragmentStateAdapter {
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
                case 4:

                    return new F_energo_2();
                default:
                    return null;
            }
        }

        @Override
        public int getItemCount() {
            return NUM_PAGES;
        }
    }
    public static class DepthPageTransformer implements ViewPager2.PageTransformer {
        private static final float MIN_SCALE = 0.75f;

        public void transformPage(View view, float position) {
            int pageWidth = view.getWidth();

            if (position < -1) { // [-Infinity,-1)
                // This page is way off-screen to the left.
                view.setAlpha(0f);

            } else if (position <= 0) { // [-1,0]
                // Use the default slide transition when moving to the left page.
                view.setAlpha(1f);
                view.setTranslationX(0f);
                view.setTranslationZ(0f);
                view.setScaleX(1f);
                view.setScaleY(1f);

            } else if (position <= 1) { // (0,1]
                // Fade the page out.
                view.setAlpha(1 - position);

                // Counteract the default slide transition.
                view.setTranslationX(pageWidth * -position);
                // Move it behind the left page
                view.setTranslationZ(-1f);

                // Scale the page down (between MIN_SCALE and 1).
                float scaleFactor = MIN_SCALE
                        + (1 - MIN_SCALE) * (1 - Math.abs(position));
                view.setScaleX(scaleFactor);
                view.setScaleY(scaleFactor);

            } else { // (1,+Infinity]
                // This page is way off-screen to the right.
                view.setAlpha(0f);
            }
        }
    }

}