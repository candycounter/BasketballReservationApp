package com.example.androidfinalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    ImageButton fullCourt1;
    ImageButton halfCourt1;
    ImageButton rules;
    Intent intent;
    String amOrpm;
    TextView todayDate;

    static final int INTENT_CODE = 12345;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fullCourt1 = findViewById(R.id.id_fullcourt1);
        fullCourt1.setImageResource(R.drawable.fullcourt);
        todayDate = findViewById(R.id.textView26);
        todayDate.setVisibility(View.GONE);
        halfCourt1 = findViewById(R.id.id_halfcourt1);
        halfCourt1.setImageResource(R.drawable.halfcourt);

        rules = findViewById(R.id.id_rules);
        rules.setImageResource(R.drawable.rules);

 /*     Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
        SimpleDateFormat hour = new SimpleDateFormat("HH");
        SimpleDateFormat min = new SimpleDateFormat("mm");
        final int hour1 = Integer.parseInt(hour.format(date));
        if(hour1 < 12){
            amOrpm = "AM";
        }
        else{
            amOrpm = "PM";
        }
        int americanHour = Integer.parseInt(hour.format(date)) % 12;
        if(americanHour==0){
            americanHour = 12;
        }
        final int intMin = Integer.parseInt(min.format(date));
        String time = americanHour+":"+min.format(date)+" "+amOrpm;

        todayDate.setText(dateFormat.format(date)+"\n\t\t"+time);
        final int finalAmericanHour = americanHour; */
        fullCourt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MainActivity.this, Main2Activity.class);
                intent.putExtra("TEST", "Full");
                startActivity(intent);
                //}

            }
        });
        halfCourt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent= new Intent(MainActivity.this, Main2Activity.class);
                intent.putExtra("TEST","Half");
                startActivity(intent);
            }
        });

        rules.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MainActivity.this, RulePage.class);
                startActivity(intent);
            }
        });


    }
}
