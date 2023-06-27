package com.example.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;


public class F_electromagn extends Fragment {

    public static final String APP_PREFERENCES = "deviceNetworkSettings";
    public static final String APP_PREFERENCES_IP = "ip_address";

    SharedPreferences mSettings;
    String ip_address;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_f_electromagn, container, false);

        mSettings = getActivity().getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        ip_address = mSettings.getString(APP_PREFERENCES_IP, "");

        ((TextView)getActivity().findViewById(R.id.textView29)).setText("Электромагн. процессы");

        Button b2 = (Button) view.findViewById(R.id.button6);

        TextView naprA = (TextView)view.findViewById((R.id.naprA));
        //naprA.setVisibility(View.GONE);

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                F_enter_regim f_enter_regim = new F_enter_regim();
                FragmentTransaction ft1 = getParentFragmentManager().beginTransaction();
                ft1.replace(R.id.frame, f_enter_regim);
                ft1.commit();
               // ((TextView)getActivity().findViewById(R.id.textTitle)).setText("Выбор режима");

            }
        });

        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                try {
                    URL url = new URL("http://" + ip_address + "/electromagn");
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET"); // установка метода получения данных -GET
                    connection.setConnectTimeout(5000); // установка таймаута перед выполнением - 10 000 миллисеку
                    connection.connect(); // подключаемся к ресурсу

                    InputStream stream = connection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(stream));

                    String JSONDeviceData = reader.readLine();
                    JSONObject obj = new JSONObject(JSONDeviceData);

                    String Ua= obj.getString("Ua");

                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            naprA.setText(Ua);
                        }
                    });


                } catch (IOException e) {

                } catch (JSONException e) {

                } finally {
                    connection.disconnect();
                }

            }
        }, 0, 1000);

        return view;
    }
}