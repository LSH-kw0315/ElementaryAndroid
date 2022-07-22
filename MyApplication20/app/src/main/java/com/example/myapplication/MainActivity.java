package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TabActivity;
import android.os.Bundle;
import android.widget.TabHost;
@SuppressWarnings("deprecation")
public class MainActivity extends TabActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TabHost tabHost=getTabHost();

        TabHost.TabSpec tabSpecFirst=tabHost.newTabSpec("TAG1").setIndicator("컵케이크");
        tabSpecFirst.setContent(R.id.first);
        tabHost.addTab(tabSpecFirst);

        TabHost.TabSpec tabSpecSecond=tabHost.newTabSpec("TAG2").setIndicator("에클레어");
        tabSpecSecond.setContent(R.id.second);
        tabHost.addTab(tabSpecSecond);

        TabHost.TabSpec tabSpecThird=tabHost.newTabSpec("TAG3").setIndicator("후로요");
        tabSpecThird.setContent(R.id.third);
        tabHost.addTab(tabSpecThird);

        TabHost.TabSpec tabSpecFourth=tabHost.newTabSpec("TAG4").setIndicator("허니콤");
        tabSpecFourth.setContent(R.id.fourth);
        tabHost.addTab(tabSpecFourth);

        tabHost.setCurrentTab(0);

     }
}