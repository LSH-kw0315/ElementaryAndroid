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
        TabHost.TabSpec tabSpecSong=tabHost.newTabSpec("TAG1").setIndicator("음악별");
        tabSpecSong.setContent(R.id.tabSong);
        tabHost.addTab(tabSpecSong);

        TabHost.TabSpec tabSpecAritist=tabHost.newTabSpec("TAG2").setIndicator("가수별");
        tabSpecAritist.setContent(R.id.tabArtist);
        tabHost.addTab(tabSpecAritist);

        TabHost.TabSpec tabSpecAlbum=tabHost.newTabSpec("TAG3").setIndicator("앨범별");
        tabSpecAlbum.setContent(R.id.tabAlbum);
        tabHost.addTab(tabSpecAlbum);

        tabHost.setCurrentTab(0);
    }
}