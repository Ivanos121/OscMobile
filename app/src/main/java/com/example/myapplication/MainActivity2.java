package com.example.myapplication;

import static android.provider.ContactsContract.CommonDataKinds.Website.URL;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.ParseException;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpEntity;
//import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class MainActivity2 extends AppCompatActivity
{
    class JSONAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected String doInBackground(String... urls) {

            String result = "none";

            CloseableHttpClient httpClient = HttpClients.createDefault();

            try {
                mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
                String ip_address = mSettings.getString(APP_PREFERENCES_IP, "");

                HttpGet request = new HttpGet("http://"+ip_address+"/request");
                CloseableHttpResponse response = httpClient.execute(request);

                try {
                    HttpEntity entity = response.getEntity();
                    if (entity != null) {
                        // return it as a String
                        result = EntityUtils.toString(entity);
                    }

                } catch (ParseException | org.apache.hc.core5.http.ParseException e) {
                    e.printStackTrace();
                } finally {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    httpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            return result;
        }

        protected void onPostExecute(String result) {
            try {
                JSONObject obj = new JSONObject(result);
                String name= obj.getString("device_name");

                LinearLayout deviceBlock = findViewById(R.id.deviceBlock);
                deviceBlock.setVisibility(View.VISIBLE);
                TextView textView8 = findViewById(R.id.textView8);
                textView8.setText(name);

                mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
                TextView textView9 = findViewById(R.id.textView9);
                textView9.setText(mSettings.getString(APP_PREFERENCES_IP, ""));

            } catch (JSONException e) {
                e.printStackTrace();

                String name= "Устройство не найдено";

                LinearLayout deviceBlock = findViewById(R.id.deviceBlock);
                deviceBlock.setVisibility(View.VISIBLE);
                TextView textView8 = findViewById(R.id.textView8);
                textView8.setText(name);

                mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
                TextView textView9 = findViewById(R.id.textView9);
                textView9.setText(mSettings.getString(APP_PREFERENCES_IP, ""));
            }
        }
    };

    TextView t1,t2,t3,t4,t5,t6;
    Button bb1;

    public static final String APP_PREFERENCES = "deviceNetworkSettings";
    public static final String APP_PREFERENCES_IP = "ip_address";

    SharedPreferences mSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);

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

        EditText editTextNumberDecimal = findViewById(R.id.editTextNumberDecimal);
        editTextNumberDecimal.setText(mSettings.getString(APP_PREFERENCES_IP, "192.168.100.10"));
    }

    public void checkDevice(View v) throws IOException {
        JSONAsyncTask task = new JSONAsyncTask();
        task.execute();
    }

    public void startThirdActivity(View v)
    {
        EditText editTextNumberDecimal = findViewById(R.id.editTextNumberDecimal);

        SharedPreferences.Editor editor = mSettings.edit();
        editor.putString(APP_PREFERENCES_IP, editTextNumberDecimal.getText().toString());
        editor.apply();

        Intent intent2 = new Intent(this, MainActivity4.class);
        startActivity(intent2);
    }

    public void openWiFiSettings(View v)
    {
        Intent intent = new Intent(Settings.ACTION_WIFI_SETTINGS);
        startActivity(intent);
    }



    private boolean checkWifiOnAndConnected()
    {
        TextView t1,t2,t3;
        ImageView im1;
        WifiManager wifiMgr = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        WifiInfo wifiInfo = wifiMgr.getConnectionInfo();
       if (wifiMgr.isWifiEnabled())
        { // Wi-Fi adapter is ON

            t1 = findViewById(R.id.textView2);
            t2 = findViewById(R.id.textView3);
            t3 = findViewById(R.id.textView4);
            im1 = findViewById(R.id.imageView);
            im1.setImageResource(R.drawable.ic_baseline_wifi_24);
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
            t2 = findViewById(R.id.textView3);
            im1 = findViewById(R.id.imageView);
            im1.setImageResource(R.drawable.ic_baseline_wifi_off);
            t2.setText("wi-fi");
            t3.setText("отключено");
            if (t1 != null) {
                t1.setText("wifi отключен или недоступен");}
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


