package com.example.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

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
import java.util.Objects;

public class F_connect extends Fragment {

    TextView t1,t2,t3,t4,t5,t6;
    Button bb1;

    public static final String APP_PREFERENCES = "deviceNetworkSettings";
    public static final String APP_PREFERENCES_IP = "ip_address";

    SharedPreferences mSettings;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment

        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), callback);
        mSettings = getActivity().getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);

        View view = inflater.inflate(R.layout.fragment_f_connect, container, false);
        Button b1 = (Button) view.findViewById(R.id.button8);

        TextView ipAddressEdit = (TextView) view.findViewById(R.id.editTextNumberDecimal);
        ipAddressEdit.setText(mSettings.getString(APP_PREFERENCES_IP, "192.168.100.10"));

        ((TextView)getActivity().findViewById(R.id.textTitle)).setText("Настройка подключения");

        TextView t1 = (TextView) view.findViewById(R.id.textView26);
        t1.setVisibility(View.GONE);

        Button b2 = (Button) view.findViewById(R.id.button7);

        b2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                ((MainActivity) requireActivity()).onFragment2NextClick();
            }
        });

        b1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = mSettings.edit();
                editor.putString(APP_PREFERENCES_IP, ipAddressEdit.getText().toString());
                editor.apply();

                set_seach_device();
            }

            private void set_seach_device() {
                //JSONAsyncTask task = new JSONAsyncTask();
                //task.execute();

                String ip_address = mSettings.getString(APP_PREFERENCES_IP, "");

                new Thread(new Runnable() {
                    public void run() {
                        HttpURLConnection connection = null;
                        try {
                            URL url = new URL("http://" + ip_address + "/request");
                            connection = (HttpURLConnection) url.openConnection();
                            connection.setRequestMethod("GET"); // установка метода получения данных -GET
                            connection.setConnectTimeout(5000); // установка таймаута перед выполнением - 10 000 миллисеку
                            connection.connect(); // подключаемся к ресурсу

                            InputStream stream = connection.getInputStream();
                            BufferedReader reader = new BufferedReader(new InputStreamReader(stream));

                            String JSONDeviceData = reader.readLine();
                            JSONObject obj = new JSONObject(JSONDeviceData);
                            String name= obj.getString("device_name");

                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    t1.setVisibility(View.VISIBLE);
                                    t1.setText(name);
                                    b2.setVisibility(View.VISIBLE);
                                }
                            });

                        } catch (MalformedURLException e) {

                        } catch (SocketTimeoutException e) {
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    t1.setVisibility(View.VISIBLE);
                                    t1.setText("Не найдено");
                                }
                            });
                        } catch (IOException e) {
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    t1.setVisibility(View.VISIBLE);
                                    t1.setText("Не найдено");
                                }
                            });
                        } catch (JSONException e) {
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    t1.setVisibility(View.VISIBLE);
                                    t1.setText("Некорректный ответ");
                                }
                            });
                        } finally {
                            connection.disconnect();
                        }
                    }
                }).start();
            }
        });

        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), callback);
        return view;
    }

    /*
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        mSettings = getActivity().getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);

        getActivity().setContentView(R.layout.activity_main2);

        DrawerLayout drawerLayout10 = requireView().findViewById(R.id.drawerLayout10);

        requireView().findViewById(R.id.imageView2).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {drawerLayout10.openDrawer(GravityCompat.START);
            }
        });

        //Переопределение шрифта
        t1=(TextView) getView().findViewById(R.id.textTitle2);
        Typeface r1=Typeface.createFromAsset(getContext().getAssets(),"fonts/Roboto-Bold.ttf");
        t1.setTypeface(r1);

        bb1=getView().findViewById(R.id.button2);
        Typeface b=Typeface.createFromAsset(getContext().getAssets(),"fonts/Roboto-Black.ttf");
        bb1.setTypeface(b);

        DrawerLayout drawerLayout = getView().findViewById(R.id.drawerLayout2);

        getView().findViewById(R.id.imageView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout10.openDrawer(GravityCompat.START);
            }
        });

        EditText editTextNumberDecimal = (EditText) getView().findViewById(R.id.editTextNumberDecimal);
        editTextNumberDecimal.setText(mSettings.getString(APP_PREFERENCES_IP, "192.168.100.10"));
    }

    public void openWiFiSettings(View v)
    {
        Intent intent = new Intent(Settings.ACTION_WIFI_SETTINGS);
        startActivity(intent);
    }*/

    OnBackPressedCallback callback = new OnBackPressedCallback(true)
    {
        @Override
        public void handleOnBackPressed()
        {
            ((MainActivity) requireActivity()).onFragment2BackClick();
        }
    };
}