package com.example.myapplication;

import static com.google.android.material.internal.ContextUtils.getActivity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    //Объявление переменных
    TextView t1,t2;
    Button b1;
    EditText editor;
    Context context;
    ImageView noWiFi;
    private DrawerLayout drawerLayout;
    private Button btn_fragment;
    private FrameLayout frameLayout;
    NavController navController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION}, 1);

    /*   btn_fragment = findViewById(R.id.btn_fragment_1);
        frameLayout = findViewById(R.id.frame);
        F_start f_start = new F_start();



        if (f_start != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.add(R.id.frame, f_start);
            ft.commit();
        }
*/


        t1 = findViewById(R.id.textView29);
        Typeface r1=Typeface.createFromAsset(getAssets(),"fonts/Roboto-Bold.ttf");
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
        F_start f_start = new F_start();
        F_connect f_connect = new F_connect();
        F_enter_regim f_enter_regim = new F_enter_regim();
        F_electromagn f_electromagn = new F_electromagn();
        F_tepl f_tepl = new F_tepl();
        F_energo f_energo = new F_energo();
        F_config f_config = new F_config();
        F_about f_about = new F_about();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.frame, new F_start()).commit();
            navigationView.setCheckedItem(R.id.f_start);
        }

    }

    @Override

    public boolean onNavigationItemSelected(@NonNull MenuItem item)
    {
        switch (item.getItemId()) {
            case R.id.f_start:
                getSupportFragmentManager().beginTransaction().replace(R.id.frame, new F_start()).commit();
                break;
                case R.id.f_connect:
                getSupportFragmentManager().beginTransaction().replace(R.id.frame, new F_connect()).commit();
                break;
            case R.id.f_enter_regim:
                getSupportFragmentManager().beginTransaction().replace(R.id.frame, new F_enter_regim()).commit();
                break;
            case R.id.f_electromagn:
                getSupportFragmentManager().beginTransaction().replace(R.id.frame, new F_electromagn()).commit();
                break;
            case R.id.f_tepl:
                getSupportFragmentManager().beginTransaction().replace(R.id.frame, new F_tepl()).commit();
                break;
            case R.id.f_vent:
                getSupportFragmentManager().beginTransaction().replace(R.id.frame, new F_vent()).commit();
                break;
            case R.id.f_energo:
                getSupportFragmentManager().beginTransaction().replace(R.id.frame, new F_energo()).commit();
                break;
            case R.id.f_config:
                getSupportFragmentManager().beginTransaction().replace(R.id.frame, new F_config()).commit();
                break;
            case R.id.f_about:
                getSupportFragmentManager().beginTransaction().replace(R.id.frame, new F_about()).commit();
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override

    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            MainActivity.super.onBackPressed();
        }
    }



    //Открытие второго листа приложения
  /*  public void startSecondActivity(View view)  {
            Intent intent = new Intent(this, MainActivity2.class);
            startActivity(intent);
        }

    public void searchWifi(MenuItem view)  {
        Intent intent2 = new Intent(this, MainActivity2.class);
        startActivity(intent2);
    }
*/
 /*   public String getScreenDensity()
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

*/

}
