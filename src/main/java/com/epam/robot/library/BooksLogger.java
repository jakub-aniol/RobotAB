package com.epam.robot.library;

import com.epam.robot.records.Book;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BooksLogger {
    private static final Logger log = LogManager.getLogger("Starting logger");

    public void log(Book book) {
        log.info(book);
    }
}
