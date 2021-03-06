package com.epam.robot.messageBus.Subscribers;

import com.epam.robot.library.BooksLoader;
import com.epam.robot.messageBus.Subscriber;
import com.epam.robot.messageBus.messages.BooksQueryMessage;

public class BooksQuerySubscriber implements Subscriber<BooksQueryMessage> {
    BooksLoader loader;

    public BooksQuerySubscriber(BooksLoader loader) {
        this.loader = loader;
        subscribe(BooksQueryMessage.class);
    }

    @Override
    public void receiveMessage(BooksQueryMessage message) {
        loader.receiveMessage(message);
    }
}
