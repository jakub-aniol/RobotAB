package com.epam.robot.url;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * This class is responsible for connect to the url address.
 *
 * @author Adrian Drabik & Bartosz Klys
 * @since 2016-03-19
 */
public class Downloader {
    private static final Logger log = LogManager.getLogger();
    private URL url;
    private static int counter;

    /**
     * Creates an object with information about URL address of future connection.
     * @param url address.
     */
    public Downloader(URL url) {
        this.url = url;
    }

    /**
     * This method starts a connection and returns a stream with content found at the url address.
     * The connection have 30 sec. timeout for downloading resources. It download a page content
     * and the close the connection.
     * @return <code>InputStream</code> object with content of the URL address.
     */
    public InputStream getStream() {
        InputStream in=null;
        try {
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setInstanceFollowRedirects(false);
            connection.setConnectTimeout(30*1000);
            connection.setRequestMethod("GET");
            connection.connect();
            in = connection.getInputStream();
            StringBuilder out = new StringBuilder();
            int c;
            while ((c=in.read())!=-1) {
                out.append((char)c);
            }
            FileOutputStream output = new FileOutputStream(new File("download"+counter));
            InputStream input = new FileInputStream(new File("download"+counter));
            counter++;
            return input;
        } catch (IOException e) {
            log.error(e.toString());
        }
        return null;
    }

    /**
     * This method returns the address in a <code>String</code> path.
     * @return <code>String</code> path of the URL.
     */
    public String getAddress() {
        return url.toString();
    }
}
