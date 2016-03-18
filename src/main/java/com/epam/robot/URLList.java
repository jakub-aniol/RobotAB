package com.epam.robot;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class URLList {
    List<URL> urls;

    public URLList() {
        urls = new ArrayList<>();
    }

    public void add(URL example) {
        urls.add(example);
    }

    public int size() {
        return urls.size();
    }

    public URL get(int i) {
        return urls.get(i);
    }
}
