package com.example.myapplication;

import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    Button button1,button2;
    LinearLayout baseLayout;
    int btnAngle=0;
    int btnScale=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        baseLayout=(LinearLayout) findViewById(R.id.BaseLayout);
        button1=(Button) findViewById(R.id.BGColorChange);
        button2=(Button) findViewById(R.id.BtnChange);
        registerForContextMenu(button1);
        registerForContextMenu(button2);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater mInflater = getMenuInflater();
        if(v==button1){
            menu.setHeaderTitle("배경색 변경");
            mInflater.inflate(R.menu.menu1,menu);
        }
        if (v==button2){
            menu.setHeaderTitle("버튼 변경");
            mInflater.inflate(R.menu.menu2,menu);
        }
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.itemRed:
                baseLayout.setBackgroundColor(Color.RED);
                return true;
            case R.id.itemGreen:
                baseLayout.setBackgroundColor(Color.GREEN);
                return true;
            case R.id.itemBlue:
                baseLayout.setBackgroundColor(Color.BLUE);
                return true;
            case R.id.subRotate:
                btnAngle+=45;
                button2.setRotation(btnAngle);
                return true;
            case R.id.subScale:
                btnScale*=2;
                button2.setScaleX(btnScale);
                return true;
            default:
                return false;
        }
    }
}