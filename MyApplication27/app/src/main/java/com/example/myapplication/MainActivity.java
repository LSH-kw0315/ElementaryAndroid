package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    View dialogView;
    Button main_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        main_btn=findViewById(R.id.main_btn);
        main_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogView=(View) View.inflate(MainActivity.this,R.layout.customlog,null);
                AlertDialog.Builder dlg=new AlertDialog.Builder(MainActivity.this);
                final Button dlg_btn=(Button)dialogView.findViewById(R.id.dig_btn);
                final TextView dlg_txt=(TextView)dialogView.findViewById(R.id.dlg_txt);
                final EditText dlg_edt=(EditText)dialogView.findViewById(R.id.dlg_edt);
                dlg_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dlg_txt.setText(dlg_edt.getText().toString());
                    }
                });
                dlg.setView(dialogView);

                dlg.setTitle("다이얼로그");
                dlg.setPositiveButton("OK",null);
                dlg.setNegativeButton("CANCLE",null);
                dlg.show();
            }
        });
    }
}