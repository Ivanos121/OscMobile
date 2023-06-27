package com.example.myapplication;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


public class F_enter_regim extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_f_enter_regim, container, false);

        ((TextView)getActivity().findViewById(R.id.textView29)).setText("Выбор режима");

        TextView tt1 = (TextView) view.findViewById(R.id.textView);

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
}