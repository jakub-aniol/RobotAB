package com.epam.robot.messageBus;

import com.epam.robot.messageBus.messages.Message;
import com.epam.robot.util.NoSubscriberException;

import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingDeque;

public class MessageWorker {
    private final BlockingDeque<Message> channelQueue;
    private final Map<Class<? extends Message>, List<Subscriber<? extends Message>>> subscribers;

    public MessageWorker(BlockingDeque<Message> channelQueue, Map<Class<? extends Message>, List<Subscriber<? extends Message>>> subscribers) {
        this.channelQueue=channelQueue;
        this.subscribers = subscribers;
    }
    public void activate (int numberOfWorkers){
        for (int i=0; i<numberOfWorkers || i<30; i++){
            new Thread(new Worker()).start();
        }
    }

    private class Worker implements Runnable {
        @Override
        public void run() {
            Message current;
            while (true){
                current = channelQueue.poll();
                if (current==null){
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                else{
                    List<Subscriber<?>> currentSubscribers = subscribers.get(current.getClass());
                    try {
                        if (currentSubscribers==null)
                        throw new NoSubscriberException("No subscriber for type of message: "+current.getClass());
                    } catch (NoSubscriberException e) {
                        e.printStackTrace();
                    }
                    for (Subscriber sub : currentSubscribers){
                        sub.receiveMessage(current);
                    }
                }
            }
        }
    }
}
