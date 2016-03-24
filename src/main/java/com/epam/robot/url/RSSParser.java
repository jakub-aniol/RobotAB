package com.epam.robot.url;

import com.epam.robot.messageBus.MessageProducer;
import com.epam.robot.messageBus.messages.FinishedQueryMessage;
import com.epam.robot.records.Book;
import com.epam.robot.urlWorker.Task;
import com.epam.robot.urlWorker.TaskProducer;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This class extract rss files from URLList object. Based on that information it can provide <code>List</code> with recent records found.
 *
 * @author Adrian Drabik & Bartosz Klys
 * @since 2016-03-18
 */
public class RSSParser implements MessageProducer, TaskProducer {
    private URLList urlList;
    private List<Book> newestBooks;
    private int taskCounter = 0;

    /**
     * This constructor creates an object and yet make no connection (creating object is lightweight).
     * @param urlList with URLs to the rss files.
     */
    public RSSParser(URLList urlList) {
        this.urlList = urlList;
        newestBooks = new ArrayList<>();
    }

    /**
     * This method connect to the addresses in URLList and looking for recent books.
     * @return <code>List</code> with recent books wrapped in <code>Book</code> object.
     */
    public List<Book> getNewestBooks() {
        processURLList();
        return Collections.unmodifiableList(newestBooks);
    }

    private void processURLList() {
        XMLHandler xmlHandler;
        Book book;
        URL url;
        DCMetadataParser parser = new DCMetadataParser();
        for (String library : urlList) {
            url = urlList.get(library);
            xmlHandler = new XMLHandler(new Downloader(url));
            offerTask(new Task(xmlHandler.getRecords(), parser, library));

        }
        send(new FinishedQueryMessage());
    }

    /*private synchronized void parseRSS(XMLHandler xmlHandler, DCMetadataParser parser, String library) {
        Book book;List<Record> list = xmlHandler.getRecords();
        for (Record r : list) {
            if (parser.isDateInvalid(r)) break;
            book = new Book(r, library);
            System.out.println(book);
            newestBooks.add(book);
            send(new CheckBookStatusMessage(book));
        }
    }*/

    public synchronized void finishedTask() {

    }
}
