package com.epam.robot.messageBus.messages;

import com.epam.robot.records.Book;

import java.util.List;

public class LoadedBooksMessage implements Message {
    private List<Book> books;

    public LoadedBooksMessage(List<Book> books) {
        this.books = books;
    }

    public String[][] getBooks() {
        String[][] out = new String[books.size()][2];
        for (int x = 0; x < out.length; x++) {
            out[x][0] = books.get(x).getLibrary();
            out[x][1] = books.get(x).getTitle();
        }
        return out;
    }
}
