package com.epam.robot;

import com.epam.robot.library.BooksLoader;
import com.epam.robot.library.BooksLogMessageSubscriber;
import com.epam.robot.library.BooksLogger;
import com.epam.robot.messageBus.Subscriber;
import com.epam.robot.messageBus.messages.FinishedQueryMessage;
import com.epam.robot.records.Book;
import com.epam.robot.ui.MainWindow;
import com.epam.robot.url.RSSParser;
import com.epam.robot.url.URLList;
import com.epam.robot.url.UserURLsReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class Start implements Subscriber<FinishedQueryMessage>{
    private static final Logger log = LogManager.getLogger("Starting logger");
    private boolean isInBackground;

    public Start() {
        isInBackground = false;
        subscribe(FinishedQueryMessage.class);
    }

    @Override
    public void receiveMessage(FinishedQueryMessage message) {
        if (isInBackground) System.exit(0);
    }

    private void start() {
        log.info("Search performed");
        new BooksLogMessageSubscriber(new BooksLogger());
        new BooksLoader(new File("books.log"));
        URLList list = UserURLsReader.loadUserURLs();
        RSSParser parser = new RSSParser(list);
        List<Book> books = parser.getNewestBooks();
    }

    public static void main(String[] args) {
        Start task = new Start();
        if (args.length>0 && args[0].equals("-b")){
            task.isInBackground= true;
            task.start();
        }
        else{
            startUI();
        }
    }

    private static void startUI() {
        new BooksLogMessageSubscriber(new BooksLogger());
        new BooksLoader(new File("books.log"));
        URLList list = UserURLsReader.loadUserURLs();
        MainWindow window = new MainWindow();
        window.setVisible(true);
    }
}
