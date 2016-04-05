package com.epam.robot.channels;

import com.epam.robot.messageBus.MessageWorker;
import com.epam.robot.messageBus.Subscriber;
import com.epam.robot.messageBus.messages.Message;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * This class is responsible for handling messages send between objects. It has a map of subscribers which are categorized by type of message they are subscribing.
 * When a {@code Message} object is sended by a {@Code MessageProducer}, it is queued to a channel and computed by a {@MessageWorker}. By default
 * there is one separate thread with worker. Any class that need to be added as a subscriber have to implement {@code Subscriber} interface and then
 * call a method {@link com.epam.robot.messageBus.Subscriber#subscribe(Class<? extends Message>)};
 *
 * @author Adrian Drabik & Bartosz Klys
 * @since 2016-03-19
 */
public class MessageChannel {

    public static final MessageChannel channel = new MessageChannel();

    private BlockingDeque<Message> channelQueue;
    private Map<Class<? extends Message>, List<Subscriber<? extends Message>>> subscribers;
    MessageWorker worker;

    private MessageChannel() {
        channelQueue = new LinkedBlockingDeque<>();
        subscribers = new HashMap<>();
    }

    public void subscribe(Class<? extends Message> messageClass, Subscriber<? extends Message> subscriber) {
        List<Subscriber<? extends Message>> list = subscribers.get(messageClass);
        if (list==null) list = new ArrayList<>();
        list.add(subscriber);
        subscribers.put(messageClass, list);
    }

    public void send(Message message) {
        if (worker==null) activateWorker();
        channelQueue.add(message);
    }

    private void activateWorker() {
        worker = new MessageWorker(channelQueue,subscribers);
        worker.activate(1);
    }
}
