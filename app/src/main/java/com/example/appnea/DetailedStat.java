package com.example.appnea;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import com.github.psambit9791.jdsp.filter.Bessel;
import com.github.psambit9791.jdsp.signal.peaks.*;
import com.github.psambit9791.jdsp.transform.Hilbert;
import com.github.psambit9791.jdsp.windows.Hamming;
import com.github.psambit9791.wavfile.WavFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

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



        try{
            InputStream ins = getResources().openRawResource(
                    getResources().getIdentifier("addi_osa_10",
                            "raw", getPackageName()));
            File tempFile = File.createTempFile("pre", "suf");
            copyFile(ins, new FileOutputStream(tempFile));

            Log.d("NOPE", "onCreate: HOLY SHIT!"+tempFile.exists());
            // Now some_file is tempFile .. do what you like
        } catch (IOException e) {
            throw new RuntimeException("Can't create temp file ", e);
        }


//        proccessSignal();
    }
    private void copyFile(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[1024];
        int read;
        while((read = in.read(buffer)) != -1){
            out.write(buffer, 0, read);
        }
    }
    private void proccessSignal(){

        double samplefreq = 44100;
        double[] signal = null;
        Bessel bandpassfilter = new Bessel(signal,samplefreq);
        double[] filteredSignal = bandpassfilter.lowPassFilter(1,150);
        Bessel newsignalfiltered = new Bessel(filteredSignal,samplefreq);
        double[] needsAmplifianceSignal = newsignalfiltered.bandPassFilter(1,200,500);
        double[] amplifiedSignal = needsAmplifianceSignal;
        for (int i=0;i<=needsAmplifianceSignal.length;i++){
            amplifiedSignal[i]=amplifiedSignal[i]*1000;
        }

        Hilbert h = new Hilbert(amplifiedSignal);
        h.hilbertTransform();
        double[][] analytical_signal = h.getOutput();
        Log.d("Endsignal", "proccessSignal: "+analytical_signal);
//        double len = 1*samplefreq;
//        int intlen = (int)len;
//        Hamming w1 = new Hamming(intlen); // For symmetric
//        double[] out = w1.getWindow();
    }
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