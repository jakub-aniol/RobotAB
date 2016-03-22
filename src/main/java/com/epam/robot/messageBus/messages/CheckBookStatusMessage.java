package com.epam.robot.messageBus.messages;

import com.epam.robot.records.Book;

public class CheckBookStatusMessage implements Message {
    Book book;

    public CheckBookStatusMessage(Book book) {
        this.book = book;
    }

    public Book getBook() {
        return book;
    }
}
