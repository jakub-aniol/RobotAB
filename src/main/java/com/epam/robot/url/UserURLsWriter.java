package com.epam.robot.url;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class UserURLsWriter {
    public static final File URLS_FILE_PATH = new File("urlList.properties");
    public static void writeURLsToFile(URLList list){
        writeURLsToFile(list,URLS_FILE_PATH);
    }
    public static void writeURLsToFile(URLList list, File file){
        Properties urls = extractProperties(list);
        try(FileOutputStream writer = new FileOutputStream(file)){
            urls.store(writer, "URL list");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Properties extractProperties(URLList list) {
        Properties urls = new Properties();
        for (String k : list){
            urls.put(k, list.get(k).toString());
        }
        return urls;
    }
}
