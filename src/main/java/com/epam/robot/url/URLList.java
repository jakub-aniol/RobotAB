package com.epam.robot.url;

import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class URLList implements Iterable<URL> {
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

    public void set(int i, URL url) {
        urls.set(i,url);
    }

    public void remove(int i) {
        urls.remove(i);
    }

    @Override
    public Iterator<URL> iterator() {
        return urls.iterator();
    }
}
