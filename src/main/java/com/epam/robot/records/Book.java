package com.epam.robot.records;

public class Book {
    String title, library;

    public Book(Record record, String library) {
        title = record.getTitle();
        this.library = library;
    }

    public Book(String line) {
        String[] data = line.split("\\|");
        if (data.length<2) throw new IllegalArgumentException("Wrong argument: " + line);
        String[] logInfo = data[0].split(" ");
        title=data[1];
        library=logInfo[logInfo.length-1];
    }

    @Override
    public String toString() {
        return library + "|"+title;
    }

    public String getLibrary() {
        return library;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj.getClass()!= Book.class) return false;
        Book other = (Book) obj;
        return title.equals(other.title) && library.equals(other.library);
    }
}
