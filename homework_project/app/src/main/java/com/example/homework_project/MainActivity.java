package com.example.homework_project;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


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

import android.widget.Toast;
import java.math.*;

public class MainActivity extends AppCompatActivity {
    GridLayout matrixA;
    LinearLayout layout1;
    Button btnMatrixASet, btnmatrixACalc;
    int[] matrixASize = {3, 3};
    double[] valMatrixA=new double[matrixASize[0]*matrixASize[1]];
    double[] valRes;
    int[] matrixResSize=new int[2];
    View set, calc;
    Intent intentA;
    final int MAT_A=1000;
    final int MAT_B=2000;
    final int MAT_C=3000;
    final int MAT_RES=4000;
    final int A_ID=10000;
    public void CreateMatrixA() {
        layout1 = (LinearLayout) findViewById(R.id.matrix1);
        matrixA = new GridLayout(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        matrixA.setLayoutParams(params);
        matrixA.setRowCount(matrixASize[0]);
        matrixA.setColumnCount(matrixASize[1]);
        for (int i = 0; i < matrixASize[0]; i++) {
            for (int j = 0; j < matrixASize[1]; j++) {
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
                editText.setId(A_ID+matrixASize[1]*i+j);
                matrixA.addView(editText);
                valMatrixA=new double[matrixASize[1]*matrixASize[1]];
            }
        }
        layout1.addView(matrixA);
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
    public void CaculateInverse(int[] size,double[] Aval){
        if(CaculateDeterminant(size,Aval)==0){
            Toast.makeText(getApplicationContext(),"역행렬이 존재하지 않습니다.",Toast.LENGTH_SHORT).show();
        }else{
            double det=CaculateDeterminant(size,Aval);
            valRes=new double[size[0]*size[1]];
            matrixResSize[0]=size[0];
            matrixResSize[1]=size[1];
            for(int i=0;i<size[0];i++){
                for(int j=0;j<size[1];j++){
                    //행렬 추출하기
                    double[] modval=new double[(size[0]-1)*(size[1]-1)];
                    int[] modsize={size[0]-1,size[1]-1};
                    int cnt=0;

                    for(int k=0;k<size[0];k++){
                        for(int l=0;l<size[1];l++){
                            if(k==i || l==j){
                                continue;
                            }else{
                                modval[cnt++]=Aval[k*size[0]+l];
                            }
                        }
                    }
                    //추출종료
                    valRes[j*size[1]+i]=Math.pow(-1,(i+1)+(j+1))*CaculateDeterminant(modsize,modval)/det;
                }
            }
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CreateMatrixA();
        btnMatrixASet=(Button)findViewById(R.id.setA);
        btnmatrixACalc=(Button)findViewById(R.id.caculateA);
        btnMatrixASet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
                dlg.setTitle("행렬 크기 설정하기");
                set = (View) View.inflate(MainActivity.this, R.layout.matrix_set, null);
                dlg.setView(set);
                dlg.setCancelable(false);
                EditText row = (EditText) set.findViewById(R.id.numrow);
                EditText column = (EditText) set.findViewById(R.id.numcolumn);
                dlg.setPositiveButton("변경", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (!(row.length() == 0 || column.length() == 0) && !(Integer.parseInt(row.getText().toString()) < 2 && Integer.parseInt(column.getText().toString()) < 2) &&!(Integer.parseInt(row.getText().toString()) == 0 || Integer.parseInt(column.getText().toString()) == 0) ) {
                            matrixASize[0] = Integer.parseInt(row.getText().toString());
                            matrixASize[1] = Integer.parseInt(column.getText().toString());
                            layout1.removeAllViews();
                            CreateMatrixA();
                        } else {
                            Toast.makeText(getApplicationContext(), "형식에 맞지 않는 입력이 있습니다.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                dlg.setNegativeButton("취소", null);
                dlg.show();

            }
        });
        btnmatrixACalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
                dlg.setTitle("행렬로 할 계산 선택");
                calc = (View) View.inflate(MainActivity.this, R.layout.matrix_calc, null);
                dlg.setView(calc);
                dlg.setNegativeButton("취소",null);
                dlg.setCancelable(false);
                AlertDialog fordel=dlg.create();
                Button matrixMultiply = (Button) calc.findViewById(R.id.matrixmultiply);
                Button matrixCramer = (Button) calc.findViewById(R.id.cramer);
                Button matrixInverse = (Button) calc.findViewById(R.id.inverse);
                Button matrixDeterminant = (Button) calc.findViewById(R.id.matrixdeterminant);
                matrixInverse.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(matrixASize[0]==matrixASize[1]){
                            EditText save;
                            for(int i=0;i<matrixASize[0];i++){
                                for(int j=0;j<matrixASize[1];j++){
                                    save=(EditText) matrixA.findViewById(A_ID+matrixASize[1]*i+j);
                                    if(!(save.getText().toString().length()==0)) {
                                        valMatrixA[matrixASize[1] * i + j] = Double.parseDouble(save.getText().toString());
                                    }else{
                                        valMatrixA[matrixASize[1]*i+j]=0f;
                                    }
                                }
                            }
                            if(CaculateDeterminant(matrixASize,valMatrixA)==0){
                                Toast.makeText(getApplicationContext(),"행렬식의 값이 0입니다.",Toast.LENGTH_SHORT).show();
                            }else {
                                CaculateInverse(matrixASize, valMatrixA);
                                if (valRes != null) {
                                    intentA = new Intent(getApplicationContext(), MatrixRes.class);
                                    intentA.putExtra("MatrixResVal", valRes);
                                    intentA.putExtra("MatrixResSize", matrixResSize);
                                    startActivityForResult(intentA, MAT_A);
                                }
                            }
                        }else{
                            Toast.makeText(getApplicationContext(),"정방행렬이어야 합니다.",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                matrixDeterminant.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(matrixASize[0]==matrixASize[1]){
                            EditText save;
                            for(int i=0;i<matrixASize[0];i++){
                                for(int j=0;j<matrixASize[1];j++){
                                    save=(EditText) matrixA.findViewById(A_ID+matrixASize[1]*i+j);
                                    if(!(save.getText().toString().length()==0) && !(save.getText().toString().length()==1 && (save.getText().toString().equals("-") || save.getText().toString().equals(".")))) {
                                        valMatrixA[matrixASize[1] * i + j] = Double.parseDouble(save.getText().toString());
                                    }else{
                                        valMatrixA[matrixASize[1]*i+j]=0f;
                                    }
                                }
                            }
                            double determinantRes=CaculateDeterminant(matrixASize,valMatrixA);
                            Toast.makeText(getApplicationContext(),"계산결과: "+String.format("%.3f",determinantRes),Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(getApplicationContext(),"정방행렬이어야 합니다.",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                matrixMultiply.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        EditText save;

                        for(int i=0;i<matrixASize[0];i++){
                            for(int j=0;j<matrixASize[1];j++){
                                save=(EditText) matrixA.findViewById(A_ID+matrixASize[1]*i+j);
                                if(!(save.getText().toString().length()==0)) {
                                    valMatrixA[matrixASize[1] * i + j] = Double.parseDouble(save.getText().toString());
                                }else{
                                    valMatrixA[matrixASize[1]*i+j]=0f;
                                }
                            }
                        }
                        intentA=new Intent(getApplicationContext(),MatrixMul.class);
                        intentA.putExtra("MatrixAVal",valMatrixA);
                        intentA.putExtra("MatrixASize",matrixASize);
                        startActivityForResult(intentA,MAT_A);

                    }
                });
                matrixCramer.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        EditText save;

                        for(int i=0;i<matrixASize[0];i++){
                            for(int j=0;j<matrixASize[1];j++){
                                save=(EditText) matrixA.findViewById(A_ID+matrixASize[1]*i+j);
                                if(!(save.getText().toString().length()==0)) {
                                    valMatrixA[matrixASize[1] * i + j] = Double.parseDouble(save.getText().toString());
                                }else{
                                    valMatrixA[matrixASize[1]*i+j]=0f;
                                }
                            }
                        }
                        if(CaculateDeterminant(matrixASize,valMatrixA)==0){
                            Toast.makeText(getApplicationContext(),"행렬식의 값이 0입니다.",Toast.LENGTH_SHORT).show();
                        }else {
                            intentA = new Intent(getApplicationContext(), MatrixCramer.class);
                            intentA.putExtra("MatrixAVal", valMatrixA);
                            intentA.putExtra("MatrixASize", matrixASize);
                            startActivityForResult(intentA, MAT_A);
                        }

                    }
                });
                dlg.show();
            }
        });

    }
}