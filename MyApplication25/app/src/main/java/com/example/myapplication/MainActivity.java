package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1=findViewById(R.id.btn1);
        final String[] versionArray = new String[] {"nuggat","oreo","gingerbread"};
        final boolean[] checkArray = new boolean[] {true, false, false};
        //전역으로 쓴 게 아니라면 이렇게 써주지 않으면 안정성이 없다고 실행을 하지 않는다.
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("안드로이드 버전이다?");
                builder.setMultiChoiceItems(versionArray, checkArray, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        btn1.setText(versionArray[which]);
                    }
                });
                builder.setPositiveButton("닫는다?",null);
                /*
                builder.setSingleChoiceItems(versionArray, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        btn1.setText(versionArray[which]);
                    }
                });
                builder.setPositiveButton("닫는다?",null);

                builder.setItems(versionArray, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        btn1.setText(versionArray[which]);
                    }
                });
                builder.setPositiveButton("확인이다?", null);

                builder.setMessage("내용이다?");
                builder.setPositiveButton("확인이다?", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this,"버튼을 눌렀다?", Toast.LENGTH_SHORT).show();
                    }
                });
                */
                builder.show();
            }
        });
    }
}