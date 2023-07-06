package com.example.myapplication;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    //Объявление переменных
    TextView t1, t2;
    Button b1;
    EditText editor;
    Context context;
    ImageView noWiFi;
    private DrawerLayout drawerLayout;
    private Button btn_fragment;
    private FrameLayout frameLayout;
    private ViewPager2 viewPager2;
    private static final int NUM_PAGES = 5;

    private LinearLayout linearLayout;

    private FragmentStateAdapter pagerAdapter;
    private FragmentTransaction frag;

    F_start f_start;
    F_connect f_connect;
    F_enter_regim f_enter_regim;
    F_electromagn f_electromagn;
    F_tepl f_tepl;
    F_energo f_energo;
    F_config f_config;
    F_about f_about;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager2 = findViewById(R.id.pager);
        viewPager2.setVisibility(View.GONE);
        pagerAdapter = new ScreenSlidePageAdapter(this);
        viewPager2.setAdapter(pagerAdapter);
        linearLayout = findViewById(R.id.list);

        FragmentManager fragmentActivity = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentActivity.beginTransaction();

        // добавляем фрагмент
        F_start ff1 = new F_start();
        fragmentTransaction.add(R.id.list, ff1);
        fragmentTransaction.commit();

        requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        t1 = findViewById(R.id.textView29);
        Typeface r1 = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Bold.ttf");
        t1.setTypeface(r1);

        //  @SuppressLint("MissingInflatedId")
        drawerLayout = findViewById(R.id.drawerLayout);
        Toolbar toolbar = findViewById(R.id.toolbars);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        NavigationView navigationView = findViewById(R.id.navigationView);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_nav, R.string.close_nav);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        frameLayout = findViewById(R.id.frame);
        f_start = new F_start();
        f_connect = new F_connect();
        //f_enter_regim = new F_enter_regim();
        //f_electromagn = new F_electromagn();
        //f_tepl = new F_tepl();
        //f_energo = new F_energo();
        f_config = new F_config();
        f_about = new F_about();

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);

                switch (position) {
                    case 0:
                        ((TextView) findViewById(R.id.textView29)).setText("Выбор режима");
                        break;
                    case 1:
                        ((TextView) findViewById(R.id.textView29)).setText("Электромагн. процессы");
                        break;
                    case 2:
                        ((TextView) findViewById(R.id.textView29)).setText("Тепловые режимы");
                        break;
                    case 3:
                        ((TextView) findViewById(R.id.textView29)).setText("Вентиляц. режимы");
                        break;
                    case 4:
                        ((TextView) findViewById(R.id.textView29)).setText("Энергетика");
                        break;
                }

            }
        });
  /*      if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.frame, new F_start()).commit();
            navigationView.setCheckedItem(R.id.f_start);
        }
*/
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.f_start:
                linearLayout.setVisibility(View.VISIBLE);
                viewPager2.setVisibility(View.GONE);

              /*  frag.replace(R.id.frame,f_start);
                frag.addToBackStack(null);
                frag.commit();*/
                getSupportFragmentManager().beginTransaction().replace(R.id.list, f_start).commit();
                linearLayout.setVisibility(View.VISIBLE);
                viewPager2.setVisibility(View.GONE);
                break;
            case R.id.f_connect:
                getSupportFragmentManager().beginTransaction().replace(R.id.list, f_connect).commit();
                break;
            case R.id.f_enter_regim:
                resieve();
                viewPager2.setCurrentItem(0);
                ((TextView) findViewById(R.id.textView29)).setText("Выбор режима");
                //getSupportFragmentManager().beginTransaction().replace(R.id.frame, new F_enter_regim()).commit();
                break;
            case R.id.f_electromagn:
                resieve();
                viewPager2.setCurrentItem(1);
                ((TextView) findViewById(R.id.textView29)).setText("Электромагн. процессы");
               // getSupportFragmentManager().beginTransaction().replace(R.id.frame, new F_electromagn()).commit();
                break;
            case R.id.f_tepl:
                resieve();
                viewPager2.setCurrentItem(2);
                ((TextView) findViewById(R.id.textView29)).setText("Тепловые режимы");
               // getSupportFragmentManager().beginTransaction().replace(R.id.frame, new F_tepl()).commit();
                break;
            case R.id.f_vent:
                resieve();
                viewPager2.setCurrentItem(3);
                ((TextView) findViewById(R.id.textView29)).setText("Вентиляц. режимы");
               // getSupportFragmentManager().beginTransaction().replace(R.id.frame, new F_vent()).commit();
                break;
            case R.id.f_energo:
                resieve();
                viewPager2.setCurrentItem(4);
                ((TextView) findViewById(R.id.textView29)).setText("Энергетика");
                //getSupportFragmentManager().beginTransaction().replace(R.id.frame, new F_energo()).commit();
                break;
            case R.id.f_config:
                ((TextView) findViewById(R.id.textView29)).setText("Настройки");
                getSupportFragmentManager().beginTransaction().replace(R.id.list, f_config).commit();
                break;
            case R.id.f_about:
                ((TextView) findViewById(R.id.textView29)).setText("О программе");
                getSupportFragmentManager().beginTransaction().replace(R.id.list, f_about).commit();
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }


 /*   @Override

    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            MainActivity.super.onBackPressed();
        }
    }
*/
   public class ScreenSlidePageAdapter extends FragmentStateAdapter {
        public ScreenSlidePageAdapter(MainActivity mainActivity) {
            super(mainActivity);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            switch (position) {
                case 0:
                    return new F_enter_regim();
                case 1:
                    return new F_electromagn();
                case 2:
                    return new F_tepl();
                case 3:
                    //((TextView) MainActivity.this.findViewById(R.id.textView29)).setText("Вентиляц. режимы");
                    return new F_vent();
                case 4:
                    return new F_energo();
                default:
                    return null;

            }
        }

        @Override
        public int getItemCount() {
            return NUM_PAGES;
        }
    }

    @Override
    public void onBackPressed()
    {
        // super.onBackPressed();
        openQuitDialog();
    }

    private void openQuitDialog() {
        AlertDialog.Builder quitDialog = new AlertDialog.Builder(
                MainActivity.this);
        quitDialog.setTitle("Выход: Вы уверены?");

        quitDialog.setPositiveButton("Да", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        quitDialog.setNegativeButton("Нет", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
            }
        });

        quitDialog.show();
    }

    void resieve()
    {
        linearLayout.setVisibility(View.GONE);
        viewPager2.setVisibility(View.VISIBLE);
    }
}

