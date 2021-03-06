package com.epam.robot.messageBus.Subscribers;

import com.epam.robot.library.BooksLoader;
import com.epam.robot.messageBus.Subscriber;
import com.epam.robot.messageBus.messages.CheckBookStatusMessage;

public class CheckBookStatusSubscriber implements Subscriber<CheckBookStatusMessage> {
    BooksLoader loader;

    public CheckBookStatusSubscriber(BooksLoader loader) {
        this.loader = loader;
        subscribe(CheckBookStatusMessage.class);
    }

    @Override
    public void receiveMessage(CheckBookStatusMessage message) {
        loader.receiveMessage(message);
    }
}
