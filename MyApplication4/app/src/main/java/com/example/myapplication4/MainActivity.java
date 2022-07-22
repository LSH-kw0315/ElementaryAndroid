package com.example.myapplication4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView text1,text2;
    CheckBox chkAgree;
    RadioGroup rGroup1;
    RadioButton rdoDog,rdoCat,rdoRabbit;
    Button btnOK;
    ImageView imgPet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text1=(TextView)findViewById(R.id.Text1);
        chkAgree=(CheckBox) findViewById(R.id.ChkAgree);

        text2=(TextView)findViewById(R.id.Text2);
        rGroup1=(RadioGroup) findViewById(R.id.Rgroup1);
        rdoDog=(RadioButton) findViewById(R.id.RdoDog);
        rdoCat=(RadioButton) findViewById(R.id.RdoCat);
        rdoRabbit=(RadioButton) findViewById(R.id.RdoRabbit);
        btnOK=(Button) findViewById(R.id.BtnOK);
        imgPet=(ImageView) findViewById(R.id.ImgPet);

        chkAgree.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(chkAgree.isChecked()){//매개변수로 넘겨준 걸 넣어도 상관없다.
                    text2.setVisibility(View.VISIBLE);
                    rGroup1.setVisibility(View.VISIBLE);
                    btnOK.setVisibility(View.VISIBLE);
                    imgPet.setVisibility(View.VISIBLE);
                }else{
                    text2.setVisibility(View.INVISIBLE);
                    rGroup1.setVisibility(View.INVISIBLE);
                    btnOK.setVisibility(View.INVISIBLE);
                    imgPet.setVisibility(View.INVISIBLE);
                }
            }
        });
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (rGroup1.getCheckedRadioButtonId()){//조건으로 할 정수는?
                    case R.id.RdoDog://이놈일 경우
                        imgPet.setImageResource(R.drawable.dog);
                        break;//안 쓰면 계속 밑으로 쭉쭉 내려감.
                    case R.id.RdoCat:
                        imgPet.setImageResource(R.drawable.cat);
                        break;
                    case R.id.RdoRabbit:
                        imgPet.setImageResource(R.drawable.rabbit);
                        break;
                    default:
                        Toast.makeText(getApplicationContext(),"동물 먼저 선택하세요.",Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}