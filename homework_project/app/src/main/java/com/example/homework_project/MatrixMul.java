package com.example.homework_project;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

public class MatrixMul extends Activity {
    final int MAT_A=1000;
    final int MAT_B=2000;
    final int MAT_C=3000;
    final int MAT_RES=4000;
    final int B_ID=20000;
    GridLayout matrix;
    LinearLayout layout1;
    int[] matrixASize=new int[2];
    double[] valMatrixA;
    int[] matrixMulSize={3,3};
    double[] valRes;
    int[] matrixResSize;
    Button matrixMulSet,goCalc,goBack;
    View set;
    Intent intentB;
    public void CreateMatrixMul() {
        layout1 = (LinearLayout) findViewById(R.id.mul);
        matrix = new GridLayout(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        matrix.setLayoutParams(params);
        matrix.setRowCount(matrixMulSize[0]);
        matrix.setColumnCount(matrixMulSize[1]);
        for (int i = 0; i < matrixMulSize[0]; i++) {
            for (int j = 0; j < matrixMulSize[1]; j++) {
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
                editText.setId(B_ID+matrixMulSize[1]*i+j);
                matrix.addView(editText);
            }
        }
        layout1.addView(matrix);
    }
    public void initArrayofMatrixA(){
        intentB=getIntent();
        double[] extractor=intentB.getDoubleArrayExtra("MatrixAVal");
        matrixASize[0]=intentB.getIntArrayExtra("MatrixASize")[0];
        matrixASize[1]=intentB.getIntArrayExtra("MatrixASize")[1];
        matrixMulSize[0]=matrixASize[1];
        valMatrixA=new double[matrixASize[0]*matrixASize[1]];
        for(int i=0;i<matrixASize[0];i++){
            for(int j=0;j<matrixASize[1];j++){
                valMatrixA[i*matrixASize[1]+j]=extractor[matrixASize[1]*i+j];
            }
        }
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.matrix_mul);
        matrixMulSet=(Button)findViewById(R.id.mulMatrixSet);
        goCalc=(Button)findViewById(R.id.goRes_mul);
        goBack=(Button)findViewById(R.id.goMain_mul);
        layout1=(LinearLayout) findViewById(R.id.mul);
        initArrayofMatrixA();
        CreateMatrixMul();
        goCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                matrixResSize=new int[2];
                matrixResSize[0]=matrixASize[0];
                matrixResSize[1]=matrixMulSize[1];
                valRes=new double[matrixResSize[0]*matrixResSize[1]];
                double[] valMatrixMul=new double[matrixMulSize[0]*matrixMulSize[1]];
                EditText save;
                for(int i=0;i<matrixMulSize[0];i++){
                    for(int j=0;j<matrixMulSize[1];j++){
                        save=(EditText)findViewById(B_ID+matrixMulSize[1]*i+j);
                        if(save.getText().toString().length()==0 || (save.getText().toString().length()==1 && (save.getText().toString().equals("-") || save.getText().toString().equals(".")))) {
                            valMatrixMul[i * matrixMulSize[1] + j] = 0;
                        }else{
                            valMatrixMul[i*matrixMulSize[1]+j]=Double.parseDouble(save.getText().toString());
                        }
                    }
                }
                for(int i=0;i<matrixResSize[0];i++){
                    for(int j=0;j<matrixResSize[1];j++){
                        valRes[i*matrixResSize[1]+j]=0;
                        for(int k=0;k<matrixASize[1];k++){
                            valRes[i*matrixResSize[1]+j]+=valMatrixA[k+i*matrixASize[1]]*valMatrixMul[k*matrixMulSize[1]+j];
                            Log.d("TAG1",String.valueOf(i*matrixResSize[1]+j));
                        }
                    }
                }
                if(valRes!=null){
                    intentB=new Intent(getApplicationContext(),MatrixRes.class);
                    intentB.putExtra("MatrixResVal",valRes);
                    intentB.putExtra("MatrixResSize",matrixResSize);
                    startActivityForResult(intentB,MAT_B);
                }else{
                    Toast.makeText(getApplicationContext(),"유효하지 않습니다.",Toast.LENGTH_SHORT).show();
                }
            }
        });
        matrixMulSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dlg = new AlertDialog.Builder(MatrixMul.this);
                dlg.setTitle("행렬 크기 설정하기");
                dlg.setMessage("행의 개수는 변경할 수 없습니다.");
                set = (View) View.inflate(MatrixMul.this, R.layout.matrix_set, null);
                dlg.setView(set);
                dlg.setCancelable(false);
                EditText row = (EditText) set.findViewById(R.id.numrow);
                EditText column = (EditText) set.findViewById(R.id.numcolumn);
                row.setEnabled(false);
                row.setText(String.valueOf(matrixASize[1]));
                dlg.setPositiveButton("변경", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (!(column.length() == 0) && !(Integer.parseInt(column.getText().toString()) < 1)) {
                            matrixMulSize[1] = Integer.parseInt(column.getText().toString());
                            layout1.removeAllViews();
                            CreateMatrixMul();
                        } else {
                            Toast.makeText(getApplicationContext(), "형식에 맞지 않는 입력이 있습니다.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                dlg.setNegativeButton("취소", null);
                dlg.show();

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
