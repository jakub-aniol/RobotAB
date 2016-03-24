package com.epam.robot.urlWorker;

import com.epam.robot.records.Record;
import com.epam.robot.url.DCMetadataParser;

import java.util.List;

public class Task {
    List<Record> list;
    DCMetadataParser parser;
    String library;

    public Task(List<Record> list, DCMetadataParser parser, String library) {
        this.list = list;
        this.parser = parser;
        this.library = library;
    }

    public List<Record> getList() {
        return list;
    }

    public DCMetadataParser getParser() {
        return parser;
    }

    public String getLibrary() {
        return library;
    }
}
