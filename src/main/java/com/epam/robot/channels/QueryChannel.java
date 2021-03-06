package com.epam.robot.channels;


import com.epam.robot.urlWorker.QueryWorker;
import com.epam.robot.urlWorker.Task;
import com.epam.robot.util.HibernateUtil;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

public class QueryChannel {
    private static final QueryChannel instance = new QueryChannel();
    private static boolean isNotInitialized = false;
    private BlockingDeque<Task> channelQueue;

    private QueryChannel() {
        initialize();
    }
    public static QueryChannel getInstance(){
        //if (isNotInitialized) instance.initialize();
        return instance;
    }

    private void initialize() {
        HibernateUtil.getSessionFactory();
        channelQueue = new LinkedBlockingDeque<>();
        new QueryWorker(channelQueue).activate(10);


    }
    public void offer(Task task){


        channelQueue.offer(task);
    }
}
