package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ViewFlipper;


public class MainActivity extends AppCompatActivity {
    Button btnStart,btnStop;
    ViewFlipper vFlip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        vFlip=findViewById(R.id.VFlipper);
        btnStart=findViewById(R.id.Start);
        btnStop=findViewById(R.id.Stop);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vFlip.startFlipping();
                vFlip.setFlipInterval(1000);
            }
        });
        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vFlip.stopFlipping();
            }
        });


    }
}