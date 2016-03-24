package com.epam.robot.messageBus.Subscribers;

import com.epam.robot.messageBus.Subscriber;
import com.epam.robot.messageBus.messages.FinishedTaskMessage;
import com.epam.robot.url.RSSParser;

public class FinishedTaskSubscriber implements Subscriber<FinishedTaskMessage> {
    private RSSParser parser;

    public FinishedTaskSubscriber(RSSParser parser) {
        this.parser = parser;
    }

    @Override
    public void receiveMessage(FinishedTaskMessage message) {
        parser.finishedTask();
    }
}
