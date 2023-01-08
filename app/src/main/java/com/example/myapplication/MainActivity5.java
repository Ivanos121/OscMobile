package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;

public class MainActivity5 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        /*Button button1 = findViewById(R.id.button5);
        button1.setOnClickListener(new View.OnClickListener() {
            Intent intent = new Intent(this, MainActivity2.class);
            startActivity(intent);
        });*/

    }

    public void endFifthActivity(View view){
        Intent intent = new Intent(this, MainActivity4.class);
        startActivity(intent);
    }


}