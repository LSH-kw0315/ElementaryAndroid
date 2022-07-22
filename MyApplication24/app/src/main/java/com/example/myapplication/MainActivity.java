package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btn1;
    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1=(Button)findViewById(R.id.button1);
        editText=(EditText) findViewById(R.id.editText1);
        registerForContextMenu(btn1);
        registerForContextMenu(editText);
    }



    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater mInflater=getMenuInflater();
        switch (v.getId()){
            case R.id.button1:
                mInflater.inflate(R.menu.menu1,menu);
                break;
            case R.id.editText1:
                menu.setHeaderTitle("텍스트 색상 변경");
                mInflater.inflate(R.menu.menu2,menu);
                break;
            default:
                break;
        }
    }
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.itembtn1:
                Toast.makeText(getApplicationContext(),item.getTitle(),Toast.LENGTH_SHORT).show();
                return true;
            case R.id.itembtn2:
                Toast.makeText(getApplicationContext(),item.getTitle(),Toast.LENGTH_SHORT).show();
                return true;
            case R.id.itemRed:
                editText.setTextColor(Color.RED);
                return true;
            case R.id.itemGreen:
                editText.setTextColor(Color.GREEN);
                return true;
            default:
                return false;
        }
    }
}