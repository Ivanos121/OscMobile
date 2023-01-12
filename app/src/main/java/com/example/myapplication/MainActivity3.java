package com.example.myapplication;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        TabLayout tabLayout = findViewById(R.id.sliding_tabs);
        ViewPager2 viewpager2 = findViewById(R.id.viewpager21);
        new TabLayoutMediator(tabLayout, viewpager2,
                (tab, position) -> tab.setText("OBJECT " + (position + 1))
        ).attach();


    }

   private void addip(){
       TextView tt1;
       tt1 = findViewById(R.id.textView9);
       WifiManager wifiMan = (WifiManager)getSystemService(WIFI_SERVICE);
       WifiInfo wifiInf = wifiMan.getConnectionInfo();
       int ipAddress = wifiInf.getIpAddress();
       String ip = String.format("%d.%d.%d.%d", (ipAddress & 0xff),(ipAddress >> 8 & 0xff),(ipAddress >> 16 & 0xff),(ipAddress >> 24 & 0xff));
       tt1.setText(ip);
   }
    @Override
    protected void onResume()
    {
        super.onResume();
        addip();
    }

    public void startForthActivity(View view)  {
        Intent intent3 = new Intent(this, MainActivity4.class);
        startActivity(intent3);
    }
}