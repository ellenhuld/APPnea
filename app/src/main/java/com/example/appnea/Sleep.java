package com.example.appnea;

import java.util.Date;

public class Sleep {

    private Date date;
    private int time;
    private String appnea;

    public Sleep(Date sleepDate, int sleepTime, String detectAppnea){

        date = sleepDate;
        time = sleepTime;
        appnea = detectAppnea;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getAppnea() {
        return appnea;
    }

    public void setAppnea(String appnea) {
        this.appnea = appnea;
    }
}
