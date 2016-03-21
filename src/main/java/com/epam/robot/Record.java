package com.epam.robot;

import java.net.MalformedURLException;
import java.net.URL;

public class Record {
    URL url;
    String title;

    public Record(String title, String url) {
        this.title = title;
        try {
            this.url = new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
