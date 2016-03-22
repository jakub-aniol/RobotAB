package com.epam.robot;

import com.epam.robot.library.BooksLoader;
import com.epam.robot.library.BooksLogMessageSubscriber;
import com.epam.robot.library.BooksLogger;
import com.epam.robot.records.Book;
import com.epam.robot.url.RSSParser;
import com.epam.robot.url.URLList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class Start {
    private static final Logger log = LogManager.getLogger("Starting logger");

    public static void main(String[] args) throws MalformedURLException {
        new BooksLogMessageSubscriber(new BooksLogger());
        new BooksLoader(new File("books.log"));
        log.info("first log");
        URLList list = new URLList();
        list.add("JBC", new URL("http://jbc.bj.uj.edu.pl/dlibra/results.rss?type=latest&dirids=1&count=100&id=rss_2.0"));
        list.add("MBC", new URL("http://mbc.malopolska.pl/dlibra/results.rss?type=latest&dirids=1&count=100&id=rss_2.0"));
        RSSParser parser = new RSSParser(list);
        List<Book> books = parser.getNewestBooks();
        for (Book book : books){
            System.out.println(book);
        }
    }
}
