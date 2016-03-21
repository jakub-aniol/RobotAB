package com.epam.robot;

import com.epam.robot.url.Downloader;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class Record {
    Downloader stream;
    String title;

    public Record(String title, String url) {
        this.title = title;
        try {
            stream = new Downloader(new URL(url));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public InputStream stream() {
        return stream.getStream();
    }
}
