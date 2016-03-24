package com.epam.robot.messageBus;

import com.epam.robot.channels.MessageChannel;
import com.epam.robot.messageBus.messages.Message;

public interface MessageProducer {
    default void send(Message message){
        MessageChannel.channel.send(message);
    }
}
