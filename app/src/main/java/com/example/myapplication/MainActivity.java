package com.example.myapplication;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity
{
    //Объявление переменных
    TextView t1;
    Button b1;
    EditText editor;
    Context context;
    ImageView noWiFi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION},1);
        NavigationView navigationView = findViewById(R.id.navigationView);
        navigationView.setItemIconTintList(null);
        //Запарет редактирования текста
        editor = findViewById(R.id.editTextTextMultiLine);
        editor.setRawInputType(0x00000000);

        //Вввод многострочного текста
        String data = "" +
                "Пример использования Linkify для создания ссылок в тексте.\n" +
                "\n" +
                "URL: http://developer.alexanderklimov.ru/ \n" +
                "Email: barsik@yanesobaka.com \n" +
                "Телефон: (495)-458-58-29 \n" +
                "Адрес: 10110 ул.Котовского, г.Мышкин \n" +
                "\n" +
                "Классно получилось?";

        if(editor != null) {
            editor.setText(data);
        }

        //Переопределение шрифта
        t1=findViewById(R.id.text1);
        Typeface r=Typeface.createFromAsset(getAssets(),"fonts/Roboto-Bold.ttf");
        t1.setTypeface(r);

        b1=findViewById(R.id.button1);
        Typeface b=Typeface.createFromAsset(getAssets(),"fonts/Roboto-Black.ttf");
        b1.setTypeface(b);

        DrawerLayout drawerLayout = findViewById(R.id.drawerLayout);

        findViewById(R.id.imageView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }

    //Открытие второго листа приложения
    public void startSecondActivity(View view)  {
            Intent intent = new Intent(this, MainActivity2.class);
            startActivity(intent);
        }

    public void searchWifi(MenuItem view)  {
        Intent intent2 = new Intent(this, MainActivity2.class);
        startActivity(intent2);
    }

}
