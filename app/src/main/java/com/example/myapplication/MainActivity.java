package com.example.myapplication;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity
{
    //Объявление переменных
    TextView t1,tt1;
    Button b1;
    EditText editor;
    Context context;
    ImageView noWiFi;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION},1);
        NavigationView navigationView = findViewById(R.id.navigationView);
        navigationView.setItemIconTintList(null);
        //Запарет редактирования текста
        tt1 = findViewById(R.id.textView19);
       //tt1.setRawInputType(0x00000000);



        //Вввод многострочного текста
        String data = "" +
                "Данное программное приложение предназначено для визуального отображения измеряемых параметров \n" +
                " в связке с аппаратным обеспечением при проведении\n" +
                "тепловентиляционных испытаний асинхронных электродвигателей\n";

        if(tt1 != null) {
            tt1.setText(data);
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

        /*   int screenSize = getResources().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK;
        switch (screenSize) {
            case Configuration.SCREENLAYOUT_SIZE_LARGE:
                Toast.makeText(this, "Большой экран", Toast.LENGTH_LONG).show();
                break;
            case Configuration.SCREENLAYOUT_SIZE_NORMAL:
                Toast.makeText(this, "Средний экран", Toast.LENGTH_LONG).show();
                break;
            case Configuration.SCREENLAYOUT_SIZE_SMALL:
                Toast.makeText(this, "Маленький экран", Toast.LENGTH_LONG).show();
                break;
            default:
                Toast.makeText(this,
                        "Размер экрана не большой, не средний и не маленький",
                        Toast.LENGTH_LONG).show();
        }*/

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

    public String getScreenDensity()
    {

        String density;
        switch (getResources().getDisplayMetrics().densityDpi)
        {
            case DisplayMetrics.DENSITY_LOW:
                density = "LDPI";

                break;
            case DisplayMetrics.DENSITY_140:
                density = "LDPI - MDPI";
                break;
            case DisplayMetrics.DENSITY_MEDIUM:
                density = "MDPI";
                break;
            case DisplayMetrics.DENSITY_180:
            case DisplayMetrics.DENSITY_200:
            case DisplayMetrics.DENSITY_220:
                density = "MDPI - HDPI";
                break;
            case DisplayMetrics.DENSITY_HIGH:
                density = "HDPI";
                break;
            case DisplayMetrics.DENSITY_260:
            case DisplayMetrics.DENSITY_280:
            case DisplayMetrics.DENSITY_300:
                density = "HDPI - XHDPI";
                break;
            case DisplayMetrics.DENSITY_XHIGH:
                density = "XHDPI";
                break;
            case DisplayMetrics.DENSITY_340:
            case DisplayMetrics.DENSITY_360:
            case DisplayMetrics.DENSITY_400:
            case DisplayMetrics.DENSITY_420:
            case DisplayMetrics.DENSITY_440:
                density = "XHDPI - XXHDPI";
                Toast.makeText(this, density, Toast.LENGTH_LONG).show();
                break;
            case DisplayMetrics.DENSITY_XXHIGH:
                density = "XXHDPI";
                break;
            case DisplayMetrics.DENSITY_560:
            case DisplayMetrics.DENSITY_600:
                density = "XXHDPI - XXXHDPI";
                break;
            case DisplayMetrics.DENSITY_XXXHIGH:
                density = "XXXHDPI";
                break;
            case DisplayMetrics.DENSITY_TV:
                density = "TVDPI";
                break;
            default:
                density = "UNKNOWN";
                break;
        }

        return density;
    }



    @Override
    protected void onResume()
    {
        super.onResume();
        getScreenDensity();
    }
}
