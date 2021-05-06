package com.example.appnea;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.media.AudioManager;
import android.media.AudioTrack;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;

import com.example.adaptors.ResultListAdaptor;

import java.util.ArrayList;
import java.util.Date;

public class ResultsActivity extends AppCompatActivity {
    private static ResultListAdaptor Adapter;
    private RecyclerView recyclerView;
    public ArrayList<ResultItem> items = new ArrayList<>();
//    public AudioManager audioManager;
    public MediaPlayer mediaPlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        mediaPlayer = MediaPlayer.create(this,R.raw.addi_osa_10);
        Populatelist();
        setupRecyclerview();
    }

    private void Populatelist() {
        ResultItem item = new ResultItem(new Date(),120,"myvideo");
        for (int i=0; i<15;i++){
            items.add(item);
        }
        MediaPlayer.TrackInfo[] info = mediaPlayer.getTrackInfo();
        Log.d("Track info", "Populatelist: "+info);
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
/*   % Importing audio
[y,Fs] = audioread('Andrea_normal_1.m4a');

% Audio normalization
ampMax = 0.05;
y = audioNormalization_YW(y,ampMax);

% Changing sampling frequency to seconds
q=length(y)/Fs;
min=floor(q/60);
sek=floor(mod(q,60));
t=linspace(0,q,length(y));

% Lowpass filter
sig = lowpass(y,150,Fs);

% Bandpass filter
signal = bandpass(sig,[200 500],Fs);

% Amplified signal
x = signal*1000;

% Square wave to get only the low frequencies
x_2 = x.^2;

%Envelope
x_3 = movmean(x_2,Fs/2);


% Segmentation
frame_duration = 1;
overlap_duration = 0.25;
frame_len = frame_duration*Fs;
overlap_len = overlap_duration*Fs;
buffered_signal = buffer(x_3, frame_len, overlap_len);

si = size(buffered_signal);

% define zeros matrix with same size as buffered_signal
classify_signal = zeros(si(2),1);

% set threshold which classifies the segment into breathing or not
% breathing. Data saved into a new matrix with 1 for breathing and 0 for
% non breathing
for j = 1:si(2)
    for k = 1:si(1)
        %
        if buffered_signal(k,j) > 0.0052
            classify_signal(j,:) = 1;
            k = 44100;
        end
    end
end

% Find apnea event by finding where we have XX non breathing (0) in
% row. apnea gives the number of apnea events detected from each signal.
count = 0;
apnea = 0;
for j = 1:si(2)
    if classify_signal(j,1) == 0
        count = count + 1;
        if count == 11 %12 %14
            apnea = apnea + 1;
            count = 0;
        end
    else
        count = 0;
    end
end*/

}