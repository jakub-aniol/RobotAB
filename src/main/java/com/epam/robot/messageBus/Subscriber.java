package com.epam.robot.messageBus;

import com.epam.robot.messageBus.messages.Message;

public interface Subscriber<T extends Message> {
    void receiveMessage(T message);
    default void subscribe(Class<? extends Message> messageClass){
        Channel.channel.subscribe(messageClass, this);
    }
}
