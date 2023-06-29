package com.example.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;


public class F_electromagn extends Fragment {

    public static final String APP_PREFERENCES = "deviceNetworkSettings";
    public static final String APP_PREFERENCES_IP = "ip_address";

    private FrameLayout frameLayout;


    SharedPreferences mSettings;
    String ip_address;

    Timer dataRequestTimer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_f_electromagn, container, false);


        mSettings = getActivity().getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        ip_address = mSettings.getString(APP_PREFERENCES_IP, "");

        ((TextView) getActivity().findViewById(R.id.textView29)).setText("Электромагн. процессы");

        Button b2 = (Button) view.findViewById(R.id.button4);

        TextView naprA = (TextView) view.findViewById((R.id.naprA));
        TextView naprB = (TextView) view.findViewById((R.id.naprB));
        TextView naprC = (TextView) view.findViewById((R.id.naprC));
        TextView tokA = (TextView) view.findViewById((R.id.tokA));
        TextView tokB = (TextView) view.findViewById((R.id.tokB));
        TextView tokC = (TextView) view.findViewById((R.id.tokC));
        TextView activA = (TextView) view.findViewById((R.id.activA));
        TextView activB = (TextView) view.findViewById((R.id.activB));
        TextView activC = (TextView) view.findViewById((R.id.activeC));
        TextView reactivA = (TextView) view.findViewById((R.id.reactivA));
        TextView reactivB = (TextView) view.findViewById((R.id.reactiveB));
        TextView reactivC = (TextView) view.findViewById((R.id.reaktivC));
        TextView polnA = (TextView) view.findViewById((R.id.polnA));
        TextView polnB = (TextView) view.findViewById((R.id.polnB));
        TextView polnC = (TextView) view.findViewById((R.id.polnC));
        TextView activ_summ = (TextView) view.findViewById((R.id.activ_summ));
        TextView reaktiv_summ = (TextView) view.findViewById((R.id.reactiv_summ));
        TextView poln_summ = (TextView) view.findViewById((R.id.poln_summ));
        TextView koeffA = (TextView) view.findViewById(R.id.koeffA);
        TextView koeffB = (TextView) view.findViewById(R.id.koeffB);
        TextView koeffC = (TextView) view.findViewById(R.id.koeffC);
        TextView koeffpoln = (TextView) view.findViewById(R.id.koeff_summ);
        TextView omegas = (TextView) view.findViewById(R.id.omega);
        TextView moments = (TextView) view.findViewById(R.id.moment);

        dataRequestTimer = new Timer();
        //naprA.setVisibility(View.GONE);

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                F_enter_regim f_enter_regim = new F_enter_regim();
                FragmentTransaction ft1 = getParentFragmentManager().beginTransaction();
                ft1.replace(R.id.frame, f_enter_regim);
                ft1.commit();
                // ((TextView)getActivity().findViewById(R.id.textTitle)).setText("Выбор режима");

            }
        });

        dataRequestTimer.scheduleAtFixedRate(new TimerTask() {
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

                    String Ua = obj.getString("Ua");
                    String Ub = obj.getString("Ub");
                    String Uc = obj.getString("Uc");
                    String Ia = obj.getString("Ia");
                    String Ib = obj.getString("Ib");
                    String Ic = obj.getString("Ic");
                    String Pa = obj.getString("Pa");
                    String Pb = obj.getString("Pb");
                    String Pc = obj.getString("Pc");
                    String Qa = obj.getString("Qa");
                    String Qb = obj.getString("Qb");
                    String Qc = obj.getString("Qc");
                    String Sa = obj.getString("Sa");
                    String Sb = obj.getString("Sb");
                    String Sc = obj.getString("Sc");
                    String Psumm = obj.getString("Psumm");
                    String Qsumm = obj.getString("Qsumm");
                    String Ssumm = obj.getString("Ssumm");
                    String coff_A = obj.getString("coff_A");
                    String coff_B = obj.getString("coff_B");
                    String coff_C = obj.getString("coff_C");
                    String coff_summ = obj.getString("coff_summ");
                    String omega = obj.getString("omega");
                    String moment = obj.getString("moment");

                    if (getActivity() != null) {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                naprA.setText(Ua);
                                naprB.setText(Ub);
                                naprC.setText(Uc);
                                tokA.setText(Ia);
                                tokB.setText(Ib);
                                tokC.setText(Ic);
                                activA.setText(Pa);
                                activB.setText(Pb);
                                activC.setText(Pc);
                                reactivA.setText(Qa);
                                reactivB.setText(Qb);
                                reactivC.setText(Qc);
                                polnA.setText(Sa);
                                polnB.setText(Sb);
                                polnC.setText(Sc);
                                activ_summ.setText(Psumm);
                                reaktiv_summ.setText(Qsumm);
                                poln_summ.setText(Ssumm);
                                koeffA.setText(coff_A);
                                koeffB.setText(coff_B);
                                koeffC.setText(coff_C);
                                koeffpoln.setText(coff_summ);
                                omegas.setText(omega);
                                moments.setText(moment);

                            }
                        });
                    }


                } catch (IOException e) {

                } catch (JSONException e) {

                } finally {
                    connection.disconnect();
                }

            }
        }, 0, 1000);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        dataRequestTimer.cancel();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_BACK) {
                        F_start fragmentFirst = new F_start();
                        FragmentManager fragmentManager = getFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.frame, fragmentFirst);
                        fragmentTransaction.commit();
                        // помечаем в драйвере первый фрагмент
                        //MainActivity.navigationView.getMenu().getItem(0).setChecked(true);
                        return true;
                    }
                }
                return false;
            }
        });
    }
}