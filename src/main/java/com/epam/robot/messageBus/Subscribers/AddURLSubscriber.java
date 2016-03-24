package com.epam.robot.messageBus.Subscribers;

import com.epam.robot.messageBus.Subscriber;
import com.epam.robot.messageBus.messages.AddURLMessage;
import com.epam.robot.url.URLList;

public class AddURLSubscriber implements Subscriber<AddURLMessage> {
    URLList subscriber;

    public AddURLSubscriber(URLList subscriber) {
        this.subscriber = subscriber;
        subscribe(AddURLMessage.class);
    }

    @Override
    public void receiveMessage(AddURLMessage message) {
        subscriber.set(message.getLibrary(), message.getAddress());
    }
}
