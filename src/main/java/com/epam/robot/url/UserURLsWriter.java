package com.epam.robot.url;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * This class has only static methods and is responsible for writing URLs to file. It depends on <code>URLList</code> objects.
 *
 * @author Adrian Drabik & Bartosz Klys
 * @since 2016-03-19
 */
public class UserURLsWriter {
    /**
     * This field stores path to .properties file with list of URLs.
     */
    public static final File URLS_FILE_PATH = new File("urlList.properties");

    /**
     * This method writes <code>URLList</code> to default file.
     *
     * @param list <code>URLList</code>
     */
    public static void writeURLsToFile(URLList list) {
        writeURLsToFile(list, URLS_FILE_PATH);
    }

    /**
     * This method writes <code>URLList</code> to provided file.
     *
     * @param list <code>URLList</code>
     * @param file <code>File</code> where <code>URLList</code> will be stored.
     */
    public static void writeURLsToFile(URLList list, File file) {
        Properties urls = extractProperties(list);
        try (FileOutputStream writer = new FileOutputStream(file)) {
            urls.store(writer, "URL list");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Properties extractProperties(URLList list) {
        Properties urls = new Properties();
        for (String k : list) {
            urls.put(k, list.get(k).toString());
        }
        return urls;
    }
}
