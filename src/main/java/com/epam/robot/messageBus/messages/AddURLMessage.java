package com.epam.robot.messageBus.messages;

public class AddURLMessage implements Message {
    String library;
    String address;

    public AddURLMessage(String library, String address) {
        this.library = library;
        this.address = address;
    }

    public String getLibrary() {
        return library;
    }

    public String getAddress() {
        return address;
    }
}
