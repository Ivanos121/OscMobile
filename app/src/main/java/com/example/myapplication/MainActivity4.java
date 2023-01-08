package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity4 extends AppCompatActivity
{

    TextView t41;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        //Переопределение шрифта
        t41 = findViewById(R.id.textTitle);
        Typeface r = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Light.ttf");
        t41.setTypeface(r);

      ImageView imageView = findViewById(R.id.imageView6);
        imageView.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity4.this, MainActivity5.class);
            startActivity(intent);
        });

        ImageView imageView2 = findViewById(R.id.imageView7);
        imageView2.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity4.this, MainActivity6.class);
            startActivity(intent);
        });

        ImageView imageView3 = findViewById(R.id.imageView8);
        imageView3.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity4.this, MainActivity7.class);
            startActivity(intent);
        });

        ImageView imageView4 = findViewById(R.id.imageView9);
        imageView4.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity4.this, MainActivity8.class);
            startActivity(intent);
        });
    }

}