package com.epam.robot.urlWorker;

import com.epam.robot.url.DCMetadataParser;
import com.epam.robot.url.XMLHandler;

public class Task {
    XMLHandler list;
    DCMetadataParser parser;
    String library;

    public Task(XMLHandler list, DCMetadataParser parser, String library) {
        this.list = list;
        this.parser = parser;
        this.library = library;
    }

    public XMLHandler getList() {
        return list;
    }

    public DCMetadataParser getParser() {
        return parser;
    }

    public String getLibrary() {
        return library;
    }
}
