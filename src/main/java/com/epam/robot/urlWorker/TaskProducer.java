package com.epam.robot.urlWorker;

import com.epam.robot.channels.QueryChannel;

public interface TaskProducer {
    default void offerTask(Task task){
        QueryChannel.getInstance().offer(task);
    }
}
