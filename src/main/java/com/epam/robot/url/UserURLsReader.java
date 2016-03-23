package com.epam.robot.url;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

public class UserURLsReader {
    public static URLList loadUserURLs(){
        return loadUserURLs(new File("urlLogs.log"));
    }
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
