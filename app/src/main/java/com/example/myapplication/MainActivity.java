package com.example.myapplication;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

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
    private FragmentTransaction frag;

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

        TabLayout tabs = findViewById(R.id.tabLayouts);
        tabs.setVisibility(View.GONE);
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
        NavigationUI.setupWithNavController(navigationView, navController);

        TextView textTitle = findViewById(R.id.textTitle);
        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController navController, @NonNull NavDestination navDestination, @Nullable Bundle bundle) {
                textTitle.setText(navDestination.getLabel());
            }
        });

        //Объявление Viewpager2
        viewPager2 = findViewById(R.id.pager);
        viewPager2.setVisibility(View.GONE);
        //  pagerAdapter = new ScreenSlidePageAdapter(this);
        viewPager2.setAdapter(pagerAdapter);
        linearLayout = findViewById(R.id.list);

        Menu menu = navigationView.getMenu();
        menu.findItem(R.id.f_electromagn).setVisible(false);
        menu.findItem(R.id.f_tepl).setVisible(false);
        menu.findItem(R.id.f_vent).setVisible(false);
        menu.findItem(R.id.f_energo).setVisible(false);


        //Настройка tablayoutbar



        //настройка прав доступа  и глобальных настроек приложения
        requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
    }
    public void onFragment1NextClick() {
        navController.navigate(R.id.action_f_start_to_f_connect);
    }

    public void onFragment2BackClick() {
        //this.findNavController().popBackStack();
        navController.navigate(R.id.action_f_connect_to_f_start);
    }

    public void onFragment2NextClick() {

        navController.navigate(R.id.action_f_connect_to_view_page_fragment);
       // menu.findItem(R.id.f_electromagn).setVisible(true);
       // menu.findItem(R.id.f_tepl).setVisible(true);
       // menu.findItem(R.id.f_vent).setVisible(true);
       // menu.findItem(R.id.f_energo).setVisible(true);

    }
}

