package com.epam.robot.url;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

/**
 * This class has only static methods and is a factory for URLList objects. It can read list of url from .properties file and based on that creates <code>URLList</code> object.
 *
 * @author Adrian Drabik & Bartosz Klys
 * @since 2016-03-19
 */

public class UserURLsReader {
    /**
     * This builder return an object based on another constructor with default file.
     *
     * @return <code>URLList</code> object.
     */
    public static URLList loadUserURLs() {
        return loadUserURLs(new File("urlList.properties"));
    }

    /**
     * This builder loads URLs from .properties file and creates <Code>URLList</Code> object.
     *
     * @param propertiesFile - <Code>File</Code> with list of addresses.
     * @return <code>URLList</code>
     */
    public static URLList loadUserURLs(File propertiesFile) {
        URLList list = new URLList();
        if (propertiesFile.exists()) {
            populateURLList(list, loadURLs(propertiesFile));
        }
        return list;
    }

    private static Properties loadURLs(File propertiesFile) {
        Properties urls = new Properties();
        try (FileInputStream in = new FileInputStream(propertiesFile)) {
            urls.load(in);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return urls;
    }

    private static void populateURLList(URLList list, Properties urls) {
        try {
            for (String k : urls.stringPropertyNames()) {
                list.add(k, new URL(urls.getProperty(k)));
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
