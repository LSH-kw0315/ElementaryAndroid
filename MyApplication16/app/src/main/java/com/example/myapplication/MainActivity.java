package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.MultiAutoCompleteTextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String[] items={"CSI-뉴욕","CSI-라스베가스","CSI-마이애미","Friends","Fringe","Lost"};
        String[] pls = {"PHP", "Perl", "Java","JavaScript", "C","C++","C#"};

        AutoCompleteTextView auto=(AutoCompleteTextView) findViewById(R.id.autoCom);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,items);
        auto.setAdapter(adapter);
        ArrayAdapter<String> adapter2=new ArrayAdapter<String>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,pls);
        MultiAutoCompleteTextView multi=(MultiAutoCompleteTextView) findViewById(R.id.multiComplete);
        MultiAutoCompleteTextView.CommaTokenizer token=new MultiAutoCompleteTextView.CommaTokenizer();
        multi.setTokenizer(token);
        multi.setAdapter(adapter2);
    }
    /* */
}