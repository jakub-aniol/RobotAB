package com.epam.robot.library;

import com.epam.robot.records.Book;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * This class logs information about founded books to <file>books.log</file> file.
 *
 * @author Adrian Drabik & Bartosz Klys
 * @since 2016-03-18
 */
public class BooksLogger {
    private static final Logger log = LogManager.getLogger(BooksLogger.class);

    /**
     * Method logs book provided in parameter.
     * @param book - this object will be logged to file.
     */
    public void log(Book book) {
        log.info(book);
    }
}
