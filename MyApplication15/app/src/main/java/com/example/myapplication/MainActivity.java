package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Chronometer;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.TimePicker;

public class MainActivity extends AppCompatActivity {
    TextView year,month,day,hour,minute;
    CalendarView calendar;
    TimePicker timePicker;
    Chronometer timer;
    RadioButton time,date;
    Button btnStart,btnEnd;
    int calYear,calMonth,calDay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        year=(TextView) findViewById(R.id.Year);
        month=(TextView) findViewById(R.id.Month);
        day=(TextView)findViewById(R.id.Day);
        hour=(TextView) findViewById(R.id.Hour);
        minute=(TextView) findViewById(R.id.Minute);
        calendar=(CalendarView) findViewById(R.id.CalendarView);
        timePicker=(TimePicker) findViewById(R.id.TimePicker);
        timer=(Chronometer) findViewById(R.id.Timer);
        time=(RadioButton) findViewById(R.id.Time);
        date=(RadioButton) findViewById(R.id.Date);
        btnStart=(Button) findViewById(R.id.Start);
        btnEnd=(Button) findViewById(R.id.End);

        timePicker.setVisibility(View.INVISIBLE);
        calendar.setVisibility(View.INVISIBLE);
        time.setVisibility(View.INVISIBLE);
        date.setVisibility(View.INVISIBLE);
        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timePicker.setVisibility(View.VISIBLE);
                calendar.setVisibility(View.INVISIBLE);
            }
        });
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timePicker.setVisibility(View.INVISIBLE);
                calendar.setVisibility(View.VISIBLE);
            }
        });

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                time.setVisibility(View.VISIBLE);
                date.setVisibility(View.VISIBLE);
                timer.setBase(SystemClock.elapsedRealtime());
                timer.setTextColor(Color.RED);
                timer.start();
            }
        });
        btnEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timer.stop();
                timer.setTextColor(Color.BLUE);
                year.setText(Integer.toString(calYear));
                month.setText(Integer.toString(calMonth));
                day.setText(Integer.toString(calDay));
                hour.setText(Integer.toString(timePicker.getCurrentHour()));
                minute.setText(Integer.toString(timePicker.getCurrentMinute()));
            }
        });

        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                calYear=year;
                calMonth=month+1;
                calDay=dayOfMonth;
            }
        });

    }
}