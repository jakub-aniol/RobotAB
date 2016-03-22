package com.epam.robot.url;

import java.net.URL;
import java.util.*;

public class URLList implements Iterable<String> {
    Map<String, URL> urls;

    public URLList() {
        urls = new HashMap<>();
    }

    public void add(String libraryName, URL example) {
        urls.put(libraryName, example);
    }

    public int size() {
        return urls.size();
    }

    public URL get(String library) {
        return urls.get(library);
    }

    public void set(String library, URL url) {
        urls.put(library, url);
    }

    public void remove(String library) {
        urls.remove(library);
    }

    @Override
    public Iterator<String> iterator() {
        return urls.keySet().iterator();
    }
}
