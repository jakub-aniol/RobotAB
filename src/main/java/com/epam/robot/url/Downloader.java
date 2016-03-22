package com.epam.robot.url;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class Downloader {
    private static final Logger log = LogManager.getLogger();
    private URL url;

    public Downloader(URL url) {
        this.url = url;
    }

    public InputStream getStream() {
        InputStream in=null;
        try {
            in = url.openStream();
        } catch (IOException e) {
            log.error(e.toString());
        }
        return in;
    }

    public String getAddress() {
        return url.toString();
    }
}
