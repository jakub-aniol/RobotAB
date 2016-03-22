package com.epam.robot.messageBus;

import com.epam.robot.messageBus.messages.Message;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

public class Channel {
    public static final Channel channel = new Channel();
    private BlockingDeque<Message> channelQueue;
    private Map<Class<? extends Message>, List<Subscriber<? extends Message>>> subscribers;
    MessageWorker worker;

    private Channel() {
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
