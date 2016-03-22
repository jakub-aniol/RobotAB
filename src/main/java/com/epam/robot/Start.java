package com.epam.robot;

import com.epam.robot.library.BooksLogMessageSubscriber;
import com.epam.robot.library.BooksLogger;
import com.epam.robot.records.Book;
import com.epam.robot.url.RSSParser;
import com.epam.robot.url.URLList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class Start {
    private static final Logger log = LogManager.getLogger("Starting logger");

    public static void main(String[] args) throws MalformedURLException {
        BooksLogMessageSubscriber subscriber = new BooksLogMessageSubscriber(new BooksLogger());
        log.info("first log");
        URLList list = new URLList();
        list.add(new URL("http://jbc.bj.uj.edu.pl/dlibra/results.rss?type=latest&dirids=1&count=100&id=rss_2.0"));
        list.add(new URL("http://mbc.malopolska.pl/dlibra/results.rss?type=latest&dirids=1&count=100&id=rss_2.0"));
        RSSParser parser = new RSSParser(list);
        List<Book> books = parser.getNewestBooks();
        for (Book book : books){
            System.out.println(book);
        }
    }
}
