package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;


public class MainActivity2 extends AppCompatActivity
{
    TextView t1,t2,t3,t4,t5,t6;
    Button bb1;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        DrawerLayout drawerLayout2 = findViewById(R.id.drawerLayout2);

        findViewById(R.id.imageView2).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                drawerLayout2.openDrawer(GravityCompat.START);
            }
        });
        //Переопределение шрифта
        t1=findViewById(R.id.textTitle2);
        Typeface r1=Typeface.createFromAsset(getAssets(),"fonts/Roboto-Bold.ttf");
        t1.setTypeface(r1);

        bb1=findViewById(R.id.button2);
        Typeface b=Typeface.createFromAsset(getAssets(),"fonts/Roboto-Black.ttf");
        bb1.setTypeface(b);

        DrawerLayout drawerLayout = findViewById(R.id.drawerLayout2);

        findViewById(R.id.imageView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout2.openDrawer(GravityCompat.START);
            }
        });
    }

    public void startThirdActivity(View v)
    {
        Intent intent2 = new Intent(this, MainActivity3.class);
        startActivity(intent2);
    }

    private boolean checkWifiOnAndConnected()
    {
        TextView t1,t2,t3;

        WifiManager wifiMgr = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);

        if (wifiMgr.isWifiEnabled())
        { // Wi-Fi adapter is ON
            WifiInfo wifiInfo = wifiMgr.getConnectionInfo();

            t1 = findViewById(R.id.textView2);
            t2 = findViewById(R.id.textView3);
            t3 = findViewById(R.id.textView4);
            String ssid  = wifiInfo.getSSID();
            t1.setText("wifi активен");
            t2.setText(ssid);
            t3.setText("включено");

                return true; // Connected to an access point
        }
        else
        {
            t1 = findViewById(R.id.textView2);
            t3 = findViewById(R.id.textView4);
            t3.setText("отключено");
            if (t1 != null) {
                t1.setText("wifi отключен");}
                return false; // Wi-Fi adapter is OFF
        }
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        checkWifiOnAndConnected();
    }
}


