package com.example.appnea;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.example.appnea.R;
import android.widget.CompoundButton;

public class RecordActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer = null;

    private Button btnTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);

        btnTest = findViewById(R.id.button_record);

        //toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

          //  @Override
            //public void onCheckedChanged(CompoundButton arg0, boolean isChecked) {
              //  text.setText("Status: " + isChecked);
            //}
        //});


        btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                readData();
            }
        });
    }



    private void readData()
    {
        //mediaPlayer = MediaPlayer.create(getBaseContext(), R.raw.addi_osa_10);
    }



}


