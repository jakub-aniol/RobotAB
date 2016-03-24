package com.epam.robot.url;

import com.epam.robot.messageBus.Subscribers.AddURLSubscriber;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * This class creates a custom map with URLs. Every entry has <code>String</code> key with the URL name. It implements <code>Iterable</code> interface and iterates by library names.
 *
 * @author Adrian Drabik & Bartosz Klys
 * @since 2016-03-19
 */
public class URLList implements Iterable<String> {
    Map<String, URL> urls;

    /**
     * This constructor initialize map.
     */
    public URLList() {
        urls = new HashMap<>();
        new AddURLSubscriber(this);
    }

    /**
     * This method add new URL to the map. This is only temporary, ie. URL won't be write to file.
     * @param libraryName - <code>String</code> with URL name.
     * @param url - <code>URL</code> object.
     */
    public void add(String libraryName, URL url) {
        urls.put(libraryName, url);
    }

    /**
     * This method returns the information about number of actual urls in the map.
     * @return <code>int</code> with size of the map.
     */
    public int size() {
        return urls.size();
    }

    /**
     * This method returns an <code>URL</code> by provided key.
     * @param library - <code>String</code> with key.
     * @return <code>URL</code>
     */
    public URL get(String library) {
        return urls.get(library);
    }

    /**
     * This method add new URL to the map. This operation can be permanent, because <code>URL</code> will be written to file.
     * @param library - key.
     * @param url - address.
     */
    public void set(String library, URL url) {
        urls.put(library, url);
        UserURLsWriter.writeURLsToFile(this);
    }

    /**
     * This method parse <code>String</code> to <code>URL</code> and add new <code>URL</code> to the map.
     * This operation can be permanent, because <code>URL</code> will be written to file.
     * @param library
     * @param url
     */
    public void set(String library, String url){
        if (!url.startsWith("http://")) url = "http://"+url;
        try {
            URL address = new URL(url);
            urls.put(library, address);
            UserURLsWriter.writeURLsToFile(this);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method remove temporarily an <code>URL</code> from the map. Address won't be deleted in a file.
     * @param library
     */
    public void remove(String library) {
        urls.remove(library);
    }

    @Override
    public Iterator<String> iterator() {
        return urls.keySet().iterator();
    }
}
