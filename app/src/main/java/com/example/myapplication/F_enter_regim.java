package com.example.myapplication;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import org.jetbrains.annotations.NotNull;


public class F_enter_regim extends Fragment {

    private ViewPager2 viewPager2;
    private static final int NUM_PAGES = 5;
    private FragmentStateAdapter pagerAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_f_enter_regim, container, false);

        ((TextView)getActivity().findViewById(R.id.textView29)).setText("Выбор режима");

        TextView tt1 = (TextView) view.findViewById(R.id.textView);

      /*  viewPager2 = getView().findViewById(R.id.pager);
        pagerAdapter = new ScreenSlidePageAdapter(this);
        viewPager2.setAdapter(pagerAdapter);*/

        tt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                F_electromagn f_electromagn = new F_electromagn();
                FragmentTransaction ft1 = getParentFragmentManager().beginTransaction();
                ft1.replace(R.id.frame, f_electromagn);
                ft1.commit();
               // ((TextView)getActivity().findViewById(R.id.textTitle)).setText("Электромагн. процессы");

            }
        });

        ImageView tt2 = (ImageView) view.findViewById(R.id.imageView6);

        tt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                F_electromagn f_electromagn = new F_electromagn();
                FragmentTransaction ft1 = getParentFragmentManager().beginTransaction();
                ft1.replace(R.id.frame, f_electromagn);
                ft1.commit();
               // ((TextView)getActivity().findViewById(R.id.textTitle)).setText("Электромагн. процессы");

            }
        });
        return view;
    }


  /*  private class ScreenSlidePageAdapter extends FragmentStateAdapter {
        public ScreenSlidePageAdapter(F_enter_regim fa) {
            super(fa);
        }

        @NonNull
        @NotNull
        @Override
        public Fragment createFragment(int position) {
            switch (position) {
                case 0:
                    return new F_enter_regim();
                case 1:
                    return new F_electromagn();
                case 2:
                    return new F_tepl();
                case 3:
                    return new F_vent();
                case 4:
                    return new F_energo();
                default:
                    return null;
                 }
        }


        @Override
        public int getItemCount() {
            return NUM_PAGES;
        }
    }*/

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_BACK) {
                        F_start fragmentFirst = new F_start();
                        FragmentManager fragmentManager = getFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.frame, fragmentFirst);
                        fragmentTransaction.commit();
                        // помечаем в драйвере первый фрагмент
                        //MainActivity.navigationView.getMenu().getItem(0).setChecked(true);
                        return true;
                    }
                }
                return false;
            }
        });
    }
}