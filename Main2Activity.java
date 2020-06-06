package com.example.androidfinalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {
    ConstraintLayout layout;
    ImageView court1;
    ImageView court2;
    ImageView court3;
    Intent intent2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        layout = findViewById(R.id.id_layout2);
        court1 = findViewById(R.id.id_court1);
        court2 = findViewById(R.id.id_court2);
        court3 = findViewById(R.id.id_court3);

        if(getIntent().getStringExtra("TEST").equals("Full")){
            layout.setBackgroundColor(Color.parseColor("#0080C6"));
            court1.setImageResource(R.drawable.fullcourt);
            court1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                intent2 = new Intent(Main2Activity.this, CalendarPage.class);
                intent2.putExtra("Length", "Full Court");
                intent2.putExtra("Court", "Court 1");
                intent2.putExtra("BACK","#0080C6");
                intent2.putExtra("Limit", ""+5);
                startActivity(intent2);
                }
            });
            court2.setImageResource(R.drawable.fullcourt);
            court2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    intent2 = new Intent(Main2Activity.this, CalendarPage.class);
                    intent2.putExtra("Court", "Court 2");
                    intent2.putExtra("Length", "Full Court");
                    intent2.putExtra("BACK","#0080C6");
                    intent2.putExtra("Limit", ""+5);
                    startActivity(intent2);
                }
            });
            court3.setImageResource(R.drawable.fullcourt);
            court3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    intent2 = new Intent(Main2Activity.this, CalendarPage.class);
                    intent2.putExtra("Court", "Court 3");
                    intent2.putExtra("Length", "Full Court");
                    intent2.putExtra("BACK","#0080C6");
                    intent2.putExtra("Limit", ""+5);
                    startActivity(intent2);
                }
            });

        }
       else if(getIntent().getStringExtra("TEST").equals("Half")){
            layout.setBackgroundColor(Color.parseColor("#FFC20E"));
            court1.setImageResource(R.drawable.halfcourt);
            court1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    intent2 = new Intent(Main2Activity.this, CalendarPage.class);
                    intent2.putExtra("Court", "Court 1");
                    intent2.putExtra("Length", "Half Court");
                    intent2.putExtra("BACK", "#FFC20E");
                    intent2.putExtra("Limit", ""+3);
                    startActivity(intent2);
                }
            });
            court2.setImageResource(R.drawable.halfcourt);
            court2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    intent2 = new Intent(Main2Activity.this, CalendarPage.class);
                    intent2.putExtra("Court", "Court 2");
                    intent2.putExtra("Length", "Half Court");
                    intent2.putExtra("BACK", "#FFC20E");
                    intent2.putExtra("Limit", ""+3);
                    startActivity(intent2);
                }
            });
            court3.setVisibility(View.INVISIBLE);
        }
    }
}
