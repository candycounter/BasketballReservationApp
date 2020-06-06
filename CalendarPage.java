package com.example.androidfinalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CalendarPage extends AppCompatActivity {
    String courtLength;
    String date;
    String courtNumber;
    String backGround;
    ConstraintLayout layout;
    TextView selectedDate;
    CalendarView calendar;
    Button selectDateButton;
    Boolean clicked = false;
    String limit;
    int selectedMonth;
    int selectedDay;
    int selectedYear;
    Intent intent5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_page);
        layout = findViewById(R.id.id_cl);
        selectedDate = findViewById(R.id.id_dateSelect);
        selectDateButton = findViewById(R.id.id_selectButton);
        backGround = getIntent().getStringExtra("BACK");
        layout.setBackgroundColor(Color.parseColor(backGround));
        courtLength = getIntent().getStringExtra("Length");
        courtNumber = getIntent().getStringExtra("Court");
        limit = getIntent().getStringExtra("Limit");
        calendar = findViewById(R.id.id_calendarView);

        Date todayDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("M-d-yyyy");
        SimpleDateFormat month = new SimpleDateFormat("M");
        final int currentMonth = Integer.parseInt(month.format(todayDate));
        SimpleDateFormat dates = new SimpleDateFormat("d");
        final int currentDate = Integer.parseInt(dates.format(todayDate));
        SimpleDateFormat year = new SimpleDateFormat("yyyy");
        final int currentYear = Integer.parseInt(year.format(todayDate));

        Log.d("DATE", String.valueOf(currentMonth)+"-"+String.valueOf(currentDate)+"-"+String.valueOf(currentYear));

        selectedDate.setText(dateFormat.format(todayDate));

        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                clicked = true;
                date = (month +1) + "-" + dayOfMonth+"-"+year;
                selectedDate.setText(date);
                selectedYear = year;
                selectedMonth = month + 1;
                selectedDay = dayOfMonth;

            }
        });

        selectDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((currentDate < selectedDay || currentDate==selectedDay) && currentMonth <= selectedMonth && currentYear <= selectedYear) {
                    intent5 = new Intent(CalendarPage.this, SlotPage.class);
                    intent5.putExtra("Court", courtNumber);
                    intent5.putExtra("Length", courtLength);
                    intent5.putExtra("Date", selectedDate.getText().toString());
                    intent5.putExtra("BACK", backGround);
                    intent5.putExtra("Limit", limit);
                    startActivity(intent5);
                } else if (!(clicked)) {
                    intent5 = new Intent(CalendarPage.this, SlotPage.class);
                    intent5.putExtra("Court", courtNumber);
                    intent5.putExtra("Length", courtLength);
                    intent5.putExtra("Date", selectedDate.getText().toString());
                    intent5.putExtra("BACK", backGround);
                    intent5.putExtra("Limit", limit);
                    startActivity(intent5);
                }else{
                    Toast.makeText(CalendarPage.this, "The date you selected has already passed!", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}