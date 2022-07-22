package com.example.myapplication3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText edit1,edit2;
    Button btnAdd,btnSub,btnMul,btnDiv,btnMod;
    TextView textResult;
    String num1,num2;
    Integer result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edit1=(EditText) findViewById(R.id.Edit1);
        edit2=(EditText) findViewById(R.id.Edit2);
        btnAdd=(Button) findViewById(R.id.BtnAdd);
        btnSub=(Button) findViewById(R.id.BtnSub);
        btnMul=(Button) findViewById(R.id.BtnMul);
        btnDiv=(Button) findViewById(R.id.BtnDiv);
        btnMod=(Button)findViewById(R.id.BtnMod);
        textResult=(TextView) findViewById(R.id.TextResult);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num1=edit1.getText().toString();
                num2=edit2.getText().toString();
                result=Integer.parseInt(num1)+Integer.parseInt(num2);
                textResult.setText("계산 결과 :"+result.toString());

            }
        });//파라미터는 익명클래스로 생성된 인스턴스.

        btnSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num1=edit1.getText().toString();
                num2=edit2.getText().toString();
                result=Integer.parseInt(num1)-Integer.parseInt(num2);
                textResult.setText("계산 결과 :"+result.toString());
            }
        });

        btnMul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num1=edit1.getText().toString();
                num2=edit2.getText().toString();
                result=Integer.parseInt(num1)*Integer.parseInt(num2);
                textResult.setText("계산 결과 :"+result.toString());
            }
        });

        btnDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num1=edit1.getText().toString();
                num2=edit2.getText().toString();
                result=Integer.parseInt(num1)/Integer.parseInt(num2);
                textResult.setText("계산 결과:"+result.toString());
                /*
                double res=(double) Integer.parseInt(num1)/Integer.parseInt(num2);
                textResult.setText("계산 결과 :"+String.valueOf(res));
                */

            }
        });
        btnMod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num1=edit1.getText().toString();
                num2=edit2.getText().toString();
                result=Integer.parseInt(num1)%Integer.parseInt(num2);
                textResult.setText("계산 결과:"+result.toString());
            }
        });
    }
}