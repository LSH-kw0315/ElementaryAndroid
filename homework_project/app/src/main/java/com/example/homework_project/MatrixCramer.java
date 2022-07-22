package com.example.homework_project;


import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

public class MatrixCramer extends Activity {
    final int MAT_A=1000;
    final int MAT_B=2000;
    final int MAT_C=3000;
    final int MAT_RES=4000;
    final int C_ID=30000;
    GridLayout matrix;
    LinearLayout layout1;
    int[] matrixASize=new int[2];
    int[] matrixCramerSize=new int[2];
    double[] valMatrixA;
    double[] valMatrixCramer;
    Button goCalc,goBack;
    Intent intentB;
    double[] valRes;
    int[] matrixResSize;
    public void CreateMatrixcramer() {
        layout1 = (LinearLayout) findViewById(R.id.cramer);
        matrix = new GridLayout(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        matrix.setLayoutParams(params);
        matrix.setRowCount(matrixCramerSize[0]);
        matrix.setColumnCount(matrixCramerSize[1]);
        for (int i = 0; i < matrixCramerSize[0]; i++) {
                EditText editText = new EditText(this);
                editText.setSingleLine(true);
                GridLayout.LayoutParams par = new GridLayout.LayoutParams();
                par.columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1, 1f);
                par.rowSpec = GridLayout.spec(GridLayout.UNDEFINED, 1, 1f);
                editText.setLayoutParams(par);
                editText.setWidth(GridLayout.LayoutParams.MATCH_PARENT);
                editText.setHeight(GridLayout.LayoutParams.MATCH_PARENT);
                //editText.setTextSize(15);
                editText.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_NUMBER_FLAG_SIGNED | InputType.TYPE_CLASS_NUMBER);
                editText.setGravity(Gravity.BOTTOM);
                editText.setId(C_ID+i);
                matrix.addView(editText);
        }
        layout1.addView(matrix);
    }
    public void initArrayofMatrixA(){
        intentB=getIntent();

        double[] extractor=intentB.getDoubleArrayExtra("MatrixAVal");
        matrixASize[0]=intentB.getIntArrayExtra("MatrixASize")[0];
        matrixASize[1]=intentB.getIntArrayExtra("MatrixASize")[1];
        matrixCramerSize[0]=matrixASize[0];
        matrixCramerSize[1]=1;
        valMatrixA=new double[matrixASize[0]*matrixASize[1]];
        for(int i=0;i<matrixASize[0];i++){
            for(int j=0;j<matrixASize[1];j++){
                valMatrixA[i*matrixASize[1]+j]=extractor[matrixASize[1]*i+j];
            }
        }
    }
    public double CaculateDeterminant(int[] size,double[] Aval){
        //어차피 정방행렬일 때만 계산이 가능하므로. 뭐든 간에 1행만을 기준으로 한다.
        double sum=0;
        if(size[0]==2){
            sum=Aval[0]*Aval[3]-Aval[1]*Aval[2];
        }else{
            for(int i=0;i<size[1];i++){
                double[] valmod=new double[(size[0]-1)*(size[1]-1)];
                int[] sizemod={size[0]-1,size[1]-1};
                int cnt=0;

                for(int k=0;k<size[0];k++){
                    for(int l=0;l<size[1];l++){
                        if(k==0 || l==i){
                            continue;
                        }else{
                            valmod[cnt++]=Aval[k*size[1]+l];
                        }
                    }
                }
                sum+=(Math.pow(-1,i))*(Aval[i])*CaculateDeterminant(sizemod,valmod);
            }
        }
        return sum;

    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.matrix_cramer);
        goCalc=(Button)findViewById(R.id.goRes_cramer);
        goBack=(Button)findViewById(R.id.goMain_cramer);
        layout1=(LinearLayout) findViewById(R.id.cramer);
        initArrayofMatrixA();
        CreateMatrixcramer();
        valMatrixCramer=new double[matrixCramerSize[0]];
        goCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText save;
                int cnt=0;
                for (int i = 0; i < matrixCramerSize[0]; i++) {
                    save = (EditText) findViewById(C_ID + i);
                    if (save.getText().toString().length() == 0 || (save.getText().toString().length() == 1 && (save.getText().toString().equals("-") || save.getText().toString().equals(".")))) {
                        valMatrixCramer[i] = 0;
                    } else {
                        valMatrixCramer[i] = Double.parseDouble(save.getText().toString());
                    }
                    if (valMatrixCramer[i] == 0) {
                        cnt++;
                    }
                }
                if(cnt>=matrixCramerSize[0]){
                    Toast.makeText(getApplicationContext(),"열 벡터가 영행렬입니다.",Toast.LENGTH_SHORT).show();
                }else {
                        //크라머 행렬 초기화
                    double[] solution = new double[matrixCramerSize[0]];
                    double[] copyMatrix = new double[matrixASize[0] * matrixASize[1]];
                    for (int i = 0; i < matrixASize[1]; i++) {
                        //매번 초기화할 수 밖에 없는 듯하다.
                        for(int n=0;n<matrixASize[0];n++){
                            for(int m=0;m<matrixASize[1];m++){
                                copyMatrix[n*matrixASize[1]+m]=valMatrixA[n*matrixASize[1]+m];
                            }
                        }
                        for (int j = 0; j < matrixCramerSize[0]; j++) {
                            copyMatrix[j * matrixASize[1] + i] = valMatrixCramer[j];
                        }
                        solution[i]=CaculateDeterminant(matrixASize,copyMatrix)/CaculateDeterminant(matrixASize,valMatrixA);
                    }
                    TextView txt=(TextView) findViewById(R.id.output);
                    String[] output=new String[matrixASize[1]];
                    String res="";
                    for(int i=0;i<matrixASize[1];i++){
                        output[i]="X"+String.valueOf(i+1)+":"+String.format("%.3f",solution[i])+" ";
                        res+=output[i];
                    }
                    txt.setText(res);
                }
            }
        });
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
