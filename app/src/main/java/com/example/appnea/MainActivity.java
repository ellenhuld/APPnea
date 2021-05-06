package com.example.appnea;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    public static final int REQUEST_CODE_RECORD = 101;
    public static final int REQUEST_CODE_RESULTS = 100;

    //keeping track of phone mode (portrait or landscape) and user mode (which view the user has selected)
    public enum PhoneMode {PORTRAIT, LANDSCAPE}
    public enum UserMode {LIST_VIEW, RECORD_VIEW, RESULTS_VIEW}
    private PhoneMode phoneMode;
    private UserMode userMode;

    //tags so we can find our fragments later
    private static final String LIST_FRAG = "list_fragment";
    private static final String RECORD_FRAG = "record_fragment";
    private static final String RESULTS_FRAG = "results_fragment";

    //the actual fragments we use
    private RecordActivity recordActivity;
    private ResultsActivity resultsActivity;
    private RecordList recordList;

    //containers we use to put our fragments in
    private LinearLayout listContainer;
    private LinearLayout detailsContainer;

    //list of the movies in the list (loaded from file)
    private ArrayList<ResultItem> resultItems;
    private int selectedSleepIndex;

    /* components */
    private Button btnRecord;
    private Button btnResults;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //get widgets
        btnRecord = findViewById(R.id.button_record);
        btnResults = findViewById(R.id.button_result);


        //setup widget behavior
        btnRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openRecord();
            }
        });

        btnResults.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openResults();
            }
        });
    }

    //get widgets
    public void initComponents(){

        Button btnRecord = findViewById(R.id.button_record);
        btnRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Button btnResults = findViewById(R.id.button_result);
        btnResults.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: for time record (Sleep time)
                //startSleeping();
            }
        });

    }

    //life cycle stuff here
    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart called");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume called");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy called");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop called");

    }


    //own methods down here
    private void openRecord(){
        Intent intentRecord = new Intent(MainActivity.this, RecordActivity.class);
        //intentRecord.putExtra("message", msg);
        //startActivity(intentActivity2);
        startActivityForResult(intentRecord, REQUEST_CODE_RECORD);
    }

    private void openResults(){
        Intent intentResults = new Intent(MainActivity.this, ResultsActivity.class);
        //intentResults.putExtra("message", msg);
        //startActivity(intentActivity2);
        startActivityForResult(intentResults, REQUEST_CODE_RESULTS);
    }










}