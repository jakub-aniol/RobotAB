package com.epam.robot.url;

import com.epam.robot.messageBus.MessageProducer;
import com.epam.robot.messageBus.messages.BookToLogMessage;
import com.epam.robot.records.Book;
import com.epam.robot.records.Record;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RSSParser implements MessageProducer {
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
                if (parser.isDateInvalid(r)) break;
                if (parser.isBook(r)){
                    newestBooks.add(new Book(r));
                    send(new BookToLogMessage(new Book(r)));
                }
            }
        }
    }
}
