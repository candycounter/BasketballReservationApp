package com.example.androidfinalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SlotPage extends AppCompatActivity {
    Button slot2to3;
    Button slot3to4;
    Button slot4to5;
    Button slot5to6;
    Button slot6to7;
    Intent intent3;
    String court;
    String limit;
    String length;
    String backGround;
    String date;
    TextView selectedDate;
    TextView courtNumber;
    ConstraintLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slot_page);
        court = getIntent().getStringExtra("Court");
        courtNumber = findViewById(R.id.id_courtNumber);
        courtNumber.setText(court);
        courtNumber.setTextColor(Color.DKGRAY);
        length = getIntent().getStringExtra("Length");
        courtNumber.setText(length+"\n"+court);
        date = getIntent().getStringExtra("Date");
        selectedDate = findViewById(R.id.id_dateSelectedd);
        selectedDate.setText("Date Selected:\n"+date);
        selectedDate.setTextColor(Color.DKGRAY);
        backGround = getIntent().getStringExtra("BACK");
        layout = findViewById(R.id.id_cola);
        layout.setBackgroundColor(Color.parseColor(backGround));
        limit = getIntent().getStringExtra("Limit");

        //Toast.makeText(SlotPage.this, "You chose "+court+"!",Toast.LENGTH_SHORT).show();
        slot2to3 = findViewById(R.id.id_2to3);
        slot2to3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent3 = new Intent(SlotPage.this, NameAndTeam.class);
                intent3.putExtra("Slot","2:00 to 3:00");
                intent3.putExtra("Court", court);
                intent3.putExtra("Length", length);
                intent3.putExtra("Date", date);
                intent3.putExtra("BACK", backGround);
                intent3.putExtra("Limit", limit);
                startActivity(intent3);
            }
        });
        slot3to4 = findViewById(R.id.id_3to4);
        slot3to4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent3 = new Intent(SlotPage.this, NameAndTeam.class);
                intent3.putExtra("Slot","3:00 to 4:00");
                intent3.putExtra("Court", court);
                intent3.putExtra("Length", length);
                intent3.putExtra("Date", date);
                intent3.putExtra("BACK", backGround);
                intent3.putExtra("Limit", limit);
                startActivity(intent3);
            }
        });
        slot4to5 = findViewById(R.id.id_4to5);
        slot4to5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent3 = new Intent(SlotPage.this, NameAndTeam.class);
                intent3.putExtra("Slot","4:00 to 5:00");
                intent3.putExtra("Court", court);
                intent3.putExtra("Length", length);
                intent3.putExtra("Date", date);
                intent3.putExtra("BACK", backGround);
                intent3.putExtra("Limit", limit);
                startActivity(intent3);
            }
        });
        slot5to6 = findViewById(R.id.id_5to6);
        slot5to6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent3 = new Intent(SlotPage.this, NameAndTeam.class);
                intent3.putExtra("Slot","5:00 to 6:00");
                intent3.putExtra("Court", court);
                intent3.putExtra("Length", length);
                intent3.putExtra("Date", date);
                intent3.putExtra("BACK", backGround);
                intent3.putExtra("Limit", limit);
                startActivity(intent3);
            }
        });
        slot6to7 = findViewById(R.id.id_6to7);
        slot6to7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent3 = new Intent(SlotPage.this, NameAndTeam.class);
                intent3.putExtra("Slot","6:00 to 7:00");
                intent3.putExtra("Court", court);
                intent3.putExtra("Length", length);
                intent3.putExtra("Date", date);
                intent3.putExtra("BACK", backGround);
                intent3.putExtra("Limit", limit);
                startActivity(intent3);
            }
        });

    }
}
