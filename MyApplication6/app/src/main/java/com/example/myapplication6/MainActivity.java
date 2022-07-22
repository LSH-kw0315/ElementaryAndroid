package com.example.myapplication6;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);// 메모리에 뷰를 올려라.
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT));
        LinearLayout baseLayout =  new LinearLayout(this);
        baseLayout.setOrientation(LinearLayout.VERTICAL);

        setContentView(baseLayout,params);
        EditText editText = new EditText(this);
        baseLayout.addView(editText);
        Button btn = new Button(this);
        btn.setText("출력하기");
        btn.setBackgroundColor(Color.YELLOW);
        baseLayout.addView(btn);
        TextView textView=new TextView(this);
        textView.setBackgroundColor(Color.MAGENTA);
        baseLayout.addView(textView);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText(editText.getText().toString());
            }
        });
    }
}