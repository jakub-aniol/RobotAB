package com.epam.robot.messageBus.Subscribers;

import com.epam.robot.library.BooksLogger;
import com.epam.robot.messageBus.Subscriber;
import com.epam.robot.messageBus.messages.BookToLogMessage;

public class BooksLogMessageSubscriber implements Subscriber<BookToLogMessage>{
    private BooksLogger booksLogger;

    public BooksLogMessageSubscriber(BooksLogger booksLogger) {
        this.booksLogger = booksLogger;
        subscribe(BookToLogMessage.class);
    }

    @Override
    public void receiveMessage(BookToLogMessage message) {
        booksLogger.log(message.getBook());

    }
}
