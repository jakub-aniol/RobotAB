package com.epam.robot.records;

import java.util.ArrayList;

/**
 * Object for storing information about book found in the library: title of the book and library name where it was found.
 *
 * @author Adrian Drabik & Bartosz Klys
 * @since 2016-03-19
 */
public class Book {
    String title, library;
    ArrayList<String> keyWords;


    /**
     * This constructor creates object based on Record class and library name.
     *
     * @param record  - from this object class uses only title.
     * @param library - library name where book was found.
     */
    public Book(Record record, String library) {
        title = record.getTitle();
        keyWords = record.getKeyWords();
        this.library = library;
    }

    /**
     * This constructor creates object based on String from log file. It looks for <code>|</code> character;
     * When not character found, throw <code>IllegalArgumentException</code>.
     *
     * @param line
     */
    public Book(String line) {
        String[] data = line.split("\\|");
        if (data.length < 2) throw new IllegalArgumentException("Wrong argument: " + line);
        String[] logInfo = data[0].split(" ");
        title = data[1];
        library = logInfo[logInfo.length - 1];
    }

    /**
     * Method return library name where book was found.
     *
     * @return <code>String</code> with library name.
     */
    public String getLibrary() {
        return library;
    }

    /**
     * Method return title of the book.
     *
     * @return <code>String</code> with title.
     */
    public String getTitle() {
        return title;
    }


    public ArrayList<String> getKeyWords() {
        return keyWords;
    }


    @Override
    public String toString() {
        return library + "|" + title + "|" + keyWords;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() != Book.class) return false;
        Book other = (Book) obj;
        return title.equals(other.title) && library.equals(other.library);
    }
}
