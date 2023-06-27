package com.example.myapplication;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class F_about extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_f_about, container, false);

        ((TextView)getActivity().findViewById(R.id.textView29)).setText("О программе");

        Button b13 = (Button) view.findViewById(R.id.button13);
        b13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                F_enter_regim f_enter_regim = new F_enter_regim();
                FragmentTransaction ft1 = getParentFragmentManager().beginTransaction();
                ft1.replace(R.id.frame, f_enter_regim);
                ft1.commit();
                ((TextView)getActivity().findViewById(R.id.textView29)).setText("Выбор режима");

            }
        });
        return view;
    }
}