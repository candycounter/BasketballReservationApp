package com.example.androidfinalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

public class RulePage extends AppCompatActivity {
    TextView ruleBook;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rule_page);
        ruleBook = findViewById(R.id.textView4);
        ruleBook.setText("Courts are open from 2 PM to 7 PM\nGames are 1 hour each. Make sure to reserve prior to coming to play\nRacial slurs or violence will result in an immediate ejection and a lifetime ban!\n\nPlease be ethical when filling out your name. Be a good fella and enter your name only once");
    }
}
