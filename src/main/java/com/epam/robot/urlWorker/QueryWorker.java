package com.epam.robot.urlWorker;

import com.epam.robot.messageBus.MessageProducer;
import com.epam.robot.messageBus.messages.CheckBookStatusMessage;
import com.epam.robot.messageBus.messages.FinishedTaskMessage;
import com.epam.robot.records.Book;
import com.epam.robot.records.Record;
import com.epam.robot.url.DCMetadataParser;
import com.epam.robot.url.XMLHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingDeque;

public class QueryWorker {

    private final BlockingDeque<Task> channelQueue;

    public QueryWorker(BlockingDeque channelQueue) {
        this.channelQueue = channelQueue;
    }

    public void activate (int numberOfWorkers){
        for (int i=0; i<numberOfWorkers || i<30; i++){
            new Thread(new Worker()).start();
        }
    }
    private class Worker implements Runnable, MessageProducer {
        @Override
        public void run() {
            Task currentTask;
            while (true){
                currentTask = channelQueue.poll();
                if (currentTask==null) try {
                    Thread.sleep(30);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                else {
                    parseRSS(currentTask.getList(), currentTask.getParser(), currentTask.getLibrary());
                }
            }
        }

        private void parseRSS(XMLHandler handler, DCMetadataParser parser, String library) {

            Book book = null;
            List<Record> list = handler.getRecords();
            List<Book> newestBooks = new ArrayList<>();

            for (Record r : list) {
                if (parser.isDateInvalid(r)) break;
                book = new Book(r, library);
                System.out.println(book);
                newestBooks.add(book);
                send(new CheckBookStatusMessage(book));
            }






            send(new FinishedTaskMessage());
        }

    }
}
