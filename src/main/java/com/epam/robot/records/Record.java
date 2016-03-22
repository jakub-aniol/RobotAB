package com.epam.robot.records;

import com.epam.robot.url.Downloader;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;

public class Record {
    Downloader stream;
    String title;
    private Date date;

    public Record(String title, String url, String description) {
        this.title = title;
        date = getDateFromDescription(description);
        try {
            stream = new Downloader(new URL(url));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public InputStream stream() {
        return stream.getStream();
    }

    @Override
    public String toString() {
        return title;
    }

    private Date getDateFromDescription(String description){
        String d = description.substring(description.length()-10);
        int year = Integer.parseInt(d.substring(0,4));
        int month = Integer.parseInt(d.substring(5,7));
        int day = Integer.parseInt(d.substring(8));
        return new Date(year-1900,month-1,day);
    }

    public Date getDate() {
        return date;
    }
}
