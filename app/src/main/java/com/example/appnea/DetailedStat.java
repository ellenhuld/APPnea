package com.example.appnea;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class DetailedStat extends AppCompatActivity {
String name;
String path;
TextView nameview;
TextView pathview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_stat);
        Intent receivedFromList = getIntent();
        name = receivedFromList.getStringExtra("recordName");
        path = receivedFromList.getStringExtra("recordPath");
        nameview = findViewById(R.id.NAME);
        pathview = findViewById(R.id.PATH);
        nameview.setText(name);
        pathview.setText(path);
    }
}