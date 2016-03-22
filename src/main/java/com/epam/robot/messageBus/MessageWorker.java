package com.epam.robot.messageBus;

import com.epam.robot.messageBus.messages.Message;

import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingDeque;

public class MessageWorker {
    private final BlockingDeque<Message> channelQueue;
    private final Map<Class<? extends Message>, List<Subscriber<?>>> subscribers;

    public MessageWorker(BlockingDeque<Message> channelQueue, Map<Class<? extends Message>, List<Subscriber<?>>> subscribers) {
        this.channelQueue=channelQueue;
        this.subscribers = subscribers;
    }
    public void activate (int numberOfWorkers){
        for (int i=0; i<numberOfWorkers || i<30; i++){
            new Thread(new Worker());
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
                    for (Subscriber sub : subscribers.get(current.getClass())){
                        sub.receiveMessage(current);
                    }
                }
            }
        }
    }
}
