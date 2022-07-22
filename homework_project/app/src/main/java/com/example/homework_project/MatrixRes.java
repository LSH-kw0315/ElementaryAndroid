package com.example.homework_project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class MatrixRes extends Activity {
    LinearLayout layout1;
    GridLayout res;
    Intent IntentR;
    Button btnGoBack;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calcres);
        btnGoBack=(Button)findViewById(R.id.goBack_Res);
        layout1=(LinearLayout) findViewById(R.id.res);
        LinearLayout.LayoutParams params1=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        res=new GridLayout(MatrixRes.this);
        res.setLayoutParams(params1);
        IntentR=getIntent();
        int[] size={IntentR.getIntArrayExtra("MatrixResSize")[0],IntentR.getIntArrayExtra("MatrixResSize")[1]};
        double[] extractor=IntentR.getDoubleArrayExtra("MatrixResVal");
        double[] val=new double[size[0]*size[1]];
        for(int i=0;i<size[0];i++){
            for(int j=0;j<size[1];j++){
                val[size[1]*i+j]=extractor[size[1]*i+j];
            }
        }
        res.setRowCount(size[0]);
        res.setColumnCount(size[1]);
        for(int i=0;i<size[0];i++){
            for(int j=0;j<size[1];j++){
                TextView txt=new TextView(MatrixRes.this);
                GridLayout.LayoutParams params2=new GridLayout.LayoutParams();
                params2.rowSpec=GridLayout.spec(GridLayout.UNDEFINED,1,1f);
                params2.columnSpec=GridLayout.spec(GridLayout.UNDEFINED,1,1f);
                txt.setLayoutParams(params2);
                txt.setText(String.format("%.3f",val[size[1]*i+j]));
                txt.setGravity(Gravity.BOTTOM);
                txt.setWidth(GridLayout.LayoutParams.WRAP_CONTENT);
                txt.setWidth(GridLayout.LayoutParams.WRAP_CONTENT);
                res.addView(txt);
            }
        }
        layout1.addView(res);
        btnGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentR=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(IntentR);
            }
        });
    }
}
