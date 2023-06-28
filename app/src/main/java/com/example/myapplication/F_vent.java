package com.example.myapplication;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class F_vent extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_f_vent, container, false);

        ((TextView)getActivity().findViewById(R.id.textView29)).setText("Вентиляция");

        Button b9 = (Button) view.findViewById(R.id.button9);
        b9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                F_enter_regim f_enter_regim = new F_enter_regim();
                FragmentTransaction ft1 = getParentFragmentManager().beginTransaction();
                ft1.replace(R.id.frame, f_enter_regim);
                ft1.commit();
                // ((TextView)getActivity().findViewById(R.id.textTitle)).setText("Выбор режима");

            }
        });
        return view;
    }

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