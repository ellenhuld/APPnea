package com.example.appnea;

import java.util.Date;

public class ResultItem {

    private Date date;
    private int length;
    private String videoName;

    public ResultItem(Date sleepDate, int sleepTimeLength, String sleepVideoName){

        date = sleepDate;
        length = sleepTimeLength;
        videoName = sleepVideoName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }
}
