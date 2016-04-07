package com.epam.robot.records;

import com.epam.robot.url.Downloader;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;

/**
 * This class holds information about found record in rss. Contains also stream with content at URL address to book description.
 *
 * @author Adrian Drabik & Bartosz Klys
 * @since 2016-03-19
 */
public class Record {
    private Downloader stream;
    private String title;
    private Date date;
    private ArrayList<String> keyWords;

    /**
     * Creates object based on three parameters.
     * @param title - title of the book.
     * @param url - address where book description can be found (in DublinCore format).
     * @param description - description about records in a library.
     * @param keyWords - contains keyWords of the book.
     */
    public Record(String title, String url, String description, ArrayList<String> keyWords) {
        this.title = title;
        date = getDateFromDescription(description);
        try {
            stream = new Downloader(new URL(url));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        this.keyWords = keyWords;

    }

    /**
     * Creates object based on three parameters.
     * @param title - title of the book.
     * @param url - address where book description can be found (in DublinCore format).
     * @param description - description about records in a library.
     */
    public Record(String title, String url, String description){
        this.title = title;
        date = getDateFromDescription(description);
        try {
            stream = new Downloader(new URL(url));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }


    /**
     * This method returns a stream with description of the book in DublinCore format.
     * @return <code>InputStream</code> with DublinCore file.
     */
    public InputStream stream() {
        return stream.getStream();
    }

    /**
     * This method returns a date when book was uploaded to the library.
     */
    private Date getDateFromDescription(String description) {
        String d = description.substring(description.length() - 10);
        int year = Integer.parseInt(d.substring(0, 4));
        int month = Integer.parseInt(d.substring(5, 7));
        int day = Integer.parseInt(d.substring(8));
        return new Date(year - 1900, month - 1, day);
    }

    /**
     * This method returns a date when book was uploaded to the library.
     * @return <code>Date</code> object with information when book was uploaded to the library.
     */
    public Date getDate() {
        return date;
    }

    /**
     * This method returns a title of the book.
     * @return <code>String</code> with title of the book.
     */
    public String getTitle() {
        return title;
    }


    /**
     * This method returns a type of the book.
     * @return <code>String</code> with title of the book.
     */
    public ArrayList<String> getKeyWords() {
        return keyWords;
    }

    /**
     * This method returns a String with the address where book description can be found.
     * @return <code>String</code> with address.
     *//*
    public String getAddress() {
        return stream.getAddress();
    }*/

    @Override
    public String toString() {
        return title;
    }
}
