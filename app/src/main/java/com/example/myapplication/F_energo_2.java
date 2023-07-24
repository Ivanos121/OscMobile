package com.example.myapplication;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;

public class F_energo_2 extends Fragment {

    private WebView vebView;
    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_f_energo_2, container, false);

        vebView = view.findViewById(R.id.vebView);
        vebView.getSettings().setJavaScriptEnabled(true);
        vebView.loadUrl("file:///android_asset/vent_flow.html"); // point it to the SVG
        vebView.setBackgroundColor(0x00000000); // set the background to transparent
        return view;
    }
}