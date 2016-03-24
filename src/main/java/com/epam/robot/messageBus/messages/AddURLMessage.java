package com.epam.robot.messageBus.messages;

import java.net.URL;

public class AddURLMessage implements Message {
    String library;
    URL address;

    public AddURLMessage(String library, URL address) {
        this.library = library;
        this.address = address;
    }

    public String getLibrary() {
        return library;
    }

    public URL getAddress() {
        return address;
    }
}
