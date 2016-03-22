package com.epam.robot.records;

/**
 * Created by bartek on 21.03.16.
 */
public class Book {
    Record record;

    public Book(Record record) {
        this.record = record;
    }

    @Override
    public String toString() {
        return record.toString();
    }
}
