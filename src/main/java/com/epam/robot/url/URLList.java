package com.epam.robot.url;

import com.epam.robot.messageBus.Subscriber;
import com.epam.robot.messageBus.Subscribers.AddURLSubscriber;
import com.epam.robot.messageBus.messages.AddURLMessage;

import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class URLList implements Iterable<String> {
    Map<String, URL> urls;

    public URLList() {
        urls = new HashMap<>();
        new AddURLSubscriber(this);
    }

    public void add(String libraryName, URL url) {
        urls.put(libraryName, url);
    }

    public int size() {
        return urls.size();
    }

    public URL get(String library) {
        return urls.get(library);
    }

    public void set(String library, URL url) {
        urls.put(library, url);
        UserURLsWriter.writeURLsToFile(this);
    }

    public void remove(String library) {
        urls.remove(library);
    }

    @Override
    public Iterator<String> iterator() {
        return urls.keySet().iterator();
    }
}
