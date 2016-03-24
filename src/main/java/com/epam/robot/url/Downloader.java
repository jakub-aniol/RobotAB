package com.epam.robot.url;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
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

    /**
     * Creates an object with information about URL address of future connection.
     * @param url address.
     */
    public Downloader(URL url) {
        this.url = url;
    }

    /**
     * This method starts a connection and returns a stream with content found at the url address.
     * @return <code>InputStream</code> object with content of the URL address.
     */
    public InputStream getStream() {
        InputStream in=null;
        try {
            in = url.openStream();
        } catch (IOException e) {
            log.error(e.toString());
        }
        return in;
    }

    /**
     * This method returns the address in a <code>String</code> path.
     * @return <code>String</code> path of the URL.
     */
    public String getAddress() {
        return url.toString();
    }
}
