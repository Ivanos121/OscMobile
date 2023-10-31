package com.example.myapplication;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;


import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

public class MainActivity extends AppCompatActivity {
        //implements TabLayout.OnTabSelectedListener {

      //Объявление переменных
    private DrawerLayout drawerLayout;
    private Button btn_fragment;
    private FrameLayout frameLayout;
    private ViewPager2 viewPager2;
    NavController navController;

    private TabLayout tabLayout;
    private static final int NUM_PAGES = 4;

    private LinearLayout linearLayout;
    private Menu menu;

    private FragmentStateAdapter pagerAdapter;

    final OnBackPressedCallback onBackPressedCallback = new OnBackPressedCallback(true)
    {
        //Вызов функции вызова диалогового окна закрытия приложения
        @Override
        public void handleOnBackPressed() {
            showAppClosingDialog();
        }

        //Объявление функции вызова диалогового окна закрытия приложения
        private void showAppClosingDialog()
        {
            //Context context = new ContextThemeWrapper(SelectImageActivity.this, R.style.AppTheme2);
            MaterialAlertDialogBuilder r = new MaterialAlertDialogBuilder(MainActivity.this);
            r.setTitle("Warning");
            r.setMessage("Do you really want to close the app?");
            r.setPositiveButton("Да", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            r.setNegativeButton("No", null);
            r.show();
        }
    };

    public MainActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //связь функции callbak и диалогового окна закрытия приложения
        getOnBackPressedDispatcher().addCallback(this, onBackPressedCallback);

        //формирование меню
        drawerLayout = findViewById(R.id.drawerLayout);
        DrawerLayout drawerLayout = findViewById(R.id.drawerLayout);
        findViewById(R.id.imageMenu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        //Объявление navcontroller
        NavigationView navigationView = findViewById(R.id.navigationView);
        navigationView.setItemIconTintList(null);

        navController = Navigation.findNavController(this, R.id.navHostFragment);
       // NavigationUI.setupWithNavController(navigationView, navController);

        NavigationUI.setupWithNavController(navigationView, navController);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("RestrictedApi")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem)
            {
                int id = menuItem.getItemId();
                if (id == R.id.f_start) {
                    navController.navigate(R.id.f_start);
                }
                if (id == R.id.f_connect) {
                    navController.navigate(R.id.f_connect);
                }
                if (id == R.id.f_electromagn) {
                      //  navController.navigate(R.id.navigation);
                 //   Log.i(TAG, "getCurrentItem " + viewPager2.getCurrentItem());
                //    (HomeListFragment) getSupportFragmentManager().findFragmentByTag(
                  //          "android:switcher:"+R.id.viewPager2+":0");


                }
                if (id == R.id.f_tepl) {
                  //  viewPager2.setCurrentItem(2,true);
                }
                if (id == R.id.f_vent) {
                  //  viewPager2.setCurrentItem(2,true);
                }
                if (id == R.id.f_energo) {
                  //  viewPager2.setCurrentItem(2,true);
                }
                if (id == R.id.f_config) {
                    navController.navigate(R.id.f_config);
                }
                if (id == R.id.f_about) {
                    navController.navigate(R.id.f_about);
                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return false;
            }

        });

        TextView textTitle = findViewById(R.id.textTitle);
        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController navController, @NonNull NavDestination navDestination, @Nullable Bundle bundle) {
                textTitle.setText(navDestination.getLabel());
            }
        });
        ((TextView) findViewById(R.id.textTitle)).setText("Начало работы");

        Menu menu = navigationView.getMenu();
      //  menu.findItem(R.id.f_electromagn).setVisible(false);
      //  menu.findItem(R.id.f_tepl).setVisible(false);
      //  menu.findItem(R.id.f_vent).setVisible(false);
      //  menu.findItem(R.id.f_energo).setVisible(false);


        //Настройка tablayoutbar



        //настройка прав доступа  и глобальных настроек приложения
        requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
    }
    public void onFragment1NextClick() {

        navController.navigate(R.id.action_f_start_to_f_connect);
        ((TextView) findViewById(R.id.textTitle)).setText("Подключение");
    }

    public void onFragment2BackClick() {
        //this.findNavController().popBackStack();
        navController.navigate(R.id.action_f_connect_to_f_start);
    }

    public void onFragment2NextClick() {

        navController.navigate(R.id.action_f_connect_to_view_page_fragment);
        //Navigation.findNavController(this,R.id.navHostFragment).navigate(R.id.action_global_f_electromagn);

        // menu.findItem(R.id.f_electromagn).setVisible(true);
       // menu.findItem(R.id.f_tepl).setVisible(true);
       // menu.findItem(R.id.f_vent).setVisible(true);
       // menu.findItem(R.id.f_energo).setVisible(true);

    }

    public void onFragment8BackClick() {
        navController.navigate(R.id.action_f_config_to_f_start);
    }
    public void onFragment9BackClick() {
        navController.navigate(R.id.action_f_about_to_f_start);
    }

    public static class Connection
    {
        private Socket mSocket = null;
        private  String  mHost   = null;
        private  int     mPort   = 0;

        public static final String LOG_TAG = "SOCKET";

        public Connection() {}

        public Connection (final String host, final int port)
        {
            this.mHost = host;
            this.mPort = port;
        }

        // Метод открытия сокета
        public void openConnection() throws Exception
        {
            // Если сокет уже открыт, то он закрывается
            closeConnection();
            try {
                // Создание сокета
                //mSocket = new Socket(mHost, mPort);
                mSocket = new Socket();
                SocketAddress socketAddress = new InetSocketAddress(mHost, mPort);
                mSocket.connect(socketAddress, 5000);
            } catch (IOException e) {
                throw new Exception("Невозможно создать сокет: "
                        + e.getMessage());
            }
        }
        /**
         * Метод закрытия сокета
         */
        public void closeConnection()
        {
            if (mSocket != null && !mSocket.isClosed()) {
                try {
                    mSocket.close();
                } catch (IOException e) {
                    Log.e(LOG_TAG, "Ошибка при закрытии сокета :"
                            + e.getMessage());
                } finally {
                    mSocket = null;
                }
            }
            mSocket = null;
        }
        /**
         * Метод отправки данных
         */
        public void sendData(byte[] data) throws Exception {
            // Проверка открытия сокета
            if (mSocket == null || mSocket.isClosed()) {
                throw new Exception("Ошибка отправки данных. " +
                        "Сокет не создан или закрыт");
            }
            // Отправка данных
            try {
                mSocket.getOutputStream().write(data);
                mSocket.getOutputStream().flush();
            } catch (IOException e) {
                throw new Exception("Ошибка отправки данных : "
                        + e.getMessage());
            }
        }
        @Override
        protected void finalize() throws Throwable
        {
            super.finalize();
            closeConnection();
        }
    }
}

