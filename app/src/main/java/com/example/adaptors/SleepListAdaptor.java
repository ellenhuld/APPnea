package com.example.adaptors;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.appnea.R;
import com.example.appnea.Sleep;

import java.util.ArrayList;

public class SleepListAdaptor extends BaseAdapter{

    Context context;
    ArrayList<Sleep> sleeps;
    Sleep sleep = null;

    @Override
    public int getCount() {
        if(sleeps !=null) {
            return sleeps.size();
        } else {
            return 0;
        }
    }

    @Override
    public Object getItem(int position) {
        if(sleeps != null) {
            return sleeps.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
