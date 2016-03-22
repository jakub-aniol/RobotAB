package com.epam.robot.url;

import com.epam.robot.records.Book;
import com.epam.robot.records.Record;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RSSParser {
    private URLList urlList;
    private List<Book> newestBooks;

    public RSSParser(URLList urlList) {
        this.urlList = urlList;
        newestBooks = new ArrayList<>();
    }

    public List<Book> getNewestBooks() {
        processURLList();
        return Collections.unmodifiableList(newestBooks);
    }

    private void processURLList() {
        XMLHandler xmlHandler;
        DCMetadataParser parser = new DCMetadataParser();
        for (URL url : urlList){
            xmlHandler = new XMLHandler(new Downloader(url));
            List<Record> list = xmlHandler.getRecords();
            for (Record r : list){
                if (parser.isBook(r)){
                    newestBooks.add(new Book(r));
                }
            }
        }
    }
}
