package com.example.myapplication;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class F_start extends Fragment {

    TextView t1;
    Button b1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_f_start, container, false);
        TextView tvPage = (TextView) view.findViewById(R.id.textView10);
        String data = "" +
                "Данное программное приложение предназначено для визуального отображения измеряемых параметров \n" +
                " в связке с аппаратным обеспечением при проведении\n" +
                "тепловентиляционных испытаний асинхронных электродвигателей\n";
        tvPage.setText(data);

        ((TextView)getActivity().findViewById(R.id.textView29)).setText("Начало работы");


        Button b1 = (Button) view.findViewById(R.id.btn_fragment_1);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                set_add_fragment();
            }
            F_connect f_connect = new F_connect();
            private void set_add_fragment() {

                FragmentTransaction ft = getParentFragmentManager().beginTransaction();
                ft.replace(R.id.frame, f_connect);
                ft.commit();
               // ((TextView)getActivity().findViewById(R.id.textTitle)).setText("Подключение");
            }
        });
        return view;
    }
}