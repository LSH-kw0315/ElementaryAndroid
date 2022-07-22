package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    String name="???";
    String passwd="???";
    Button btn1;
    TextView textRes;
    View dialogView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1=(Button) findViewById(R.id.btn1);
        textRes=(TextView) findViewById(R.id.textRes);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogView=(View) View.inflate(MainActivity.this,R.layout.dialog_login,null);
                AlertDialog.Builder dlg=new AlertDialog.Builder(MainActivity.this);
                dlg.setView(dialogView);
                dlg.setTitle("사용자 정보");
                dlg.setPositiveButton("액세스", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        final EditText dlgID=(EditText) dialogView.findViewById(R.id.editText1);
                        final EditText dlgPW=(EditText) dialogView.findViewById(R.id.editText2);
                        name=dlgID.getText().toString();
                        passwd=dlgPW.getText().toString();
                        textRes.setText("이름: "+name+"\n"+"암호: "+passwd);
                    }
                });
                dlg.setNegativeButton("디나이얼",null);
                dlg.show();
            }
        });
    }
}