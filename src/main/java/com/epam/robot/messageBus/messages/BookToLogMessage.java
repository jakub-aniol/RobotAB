package com.epam.robot.messageBus.messages;

import com.epam.robot.records.Book;

public class BookToLogMessage implements Message {
    private Book book;

    public BookToLogMessage(Book book) {
        this.book = book;
    }

    public Book getBook() {
        return book;
    }
}
