package com.example.appnea;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import com.example.adaptors.ResultListAdaptor;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Date;

import static android.media.MediaMetadataRetriever.METADATA_KEY_DURATION;

public class ResultsActivity extends AppCompatActivity {
    private static ResultListAdaptor Adapter;
    private RecyclerView recyclerView;
    public ArrayList<ResultItem> items = new ArrayList<>();
//    public AudioManager audioManager;
    public MediaPlayer mediaPlayer;
    public MediaMetadataRetriever metadataRetriever;
    private File[] allFiles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        mediaPlayer = MediaPlayer.create(this,R.raw.addi_osa_10);
        metadataRetriever = new MediaMetadataRetriever();
        Populatelist();
        setupRecyclerview();
        Adapter.setOnItemClickListener(new ResultListAdaptor.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
               Intent intent = new Intent(ResultsActivity.this,DetailedStat.class);
               intent.putExtra("recordName",allFiles[position].getName());
               intent.putExtra("recordPath",allFiles[position].getAbsolutePath());
               startActivity(intent);
            }
        });
    }

    private void Populatelist() {
        String path = getExternalFilesDir("/").getAbsolutePath();
        File directory = new File(path);
        allFiles = directory.listFiles();

        for (File file : allFiles) {
            metadataRetriever.setDataSource(file.getAbsolutePath());
            String duration = metadataRetriever.extractMetadata(METADATA_KEY_DURATION);
            ResultItem item2 = new ResultItem(Integer.parseInt(duration)/1000,file.getName());
            items.add(item2);
        }

    }

    private void setupRecyclerview() {
        //Setup recyclerview and attach layout and adapter + EXIT button
        recyclerView = findViewById(R.id.RecyclerResultView);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManagerListActivity = new LinearLayoutManager(this);
        Adapter = new ResultListAdaptor(this,items);
        recyclerView.setLayoutManager(layoutManagerListActivity);
        recyclerView.setAdapter(Adapter);
        Adapter.notifyDataSetChanged();
    }



}