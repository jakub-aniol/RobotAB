package com.epam.robot.library;

import com.epam.robot.messageBus.MessageProducer;
import com.epam.robot.messageBus.Subscribers.BooksQuerySubscriber;
import com.epam.robot.messageBus.Subscribers.CheckBookStatusSubscriber;
import com.epam.robot.messageBus.messages.BookToLogMessage;
import com.epam.robot.messageBus.messages.BooksQueryMessage;
import com.epam.robot.messageBus.messages.CheckBookStatusMessage;
import com.epam.robot.messageBus.messages.LoadedBooksMessage;
import com.epam.robot.records.Book;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
/**
 * This class read already logged books from .log file. When no file founded, list of books has size 0.
 * @author Adrian Drabik & Bartosz Klys
 * @since 2016-03-19
 */

public final class BooksLoader implements MessageProducer {
    List<Book> books;

    /**
     * Constructor creates BooksLoader object based of information founded in file.
     * @param file - no default file for this class.
     */
    public BooksLoader(File file) {
        new CheckBookStatusSubscriber(this);
        new BooksQuerySubscriber(this);
        books = new ArrayList<>();
        if (!file.exists()) return;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)))) {
            String line;
            while ((line = reader.readLine()) !=null){
                books.add(new Book(line));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method provides information about number of already logged books.
     * @return int number of books logged.
     */
    public int numberOfBooks() {
        return books.size();
    }
    public Book get(int i){
        return books.get(i);
    }
    public boolean isNotLogged(Book book){
        for (Book b : books){
            if (b.equals(book)) return false;
        }
        return true;
    }

    /**
     * Method for subscriber helper class.
     * @param message from subscriber class.
     */
    public void receiveMessage(CheckBookStatusMessage message) {
        if (isNotLogged(message.getBook())) send(new BookToLogMessage(message.getBook()));
    }

    /**
     * Method for subscriber helper class.
     * @param message from subscriber class.
     */
    public void receiveMessage(BooksQueryMessage message) {
        send(new LoadedBooksMessage(books));
    }
}
