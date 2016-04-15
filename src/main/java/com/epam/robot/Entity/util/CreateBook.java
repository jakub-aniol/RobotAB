package com.epam.robot.Entity.util;

import com.epam.robot.Entity.DBBooks.DBBooks;
import com.epam.robot.records.Book;
import org.hibernate.Session;

import java.util.Date;

public class CreateBook {

    public void addingBook(Book book, Session session) {

        System.out.println("Creating Book");

        // Create session factory object

        // getting transaction object from session object
        //session.beginTransaction();

        DBBooks bookDB = new DBBooks(book.getLibrary(), book.getTitle(), book.getType(), book.getKeyWords().toString());


        System.out.println(book.getLibrary());

        session.getTransaction().begin();

        bookDB.setCreatedDate(new Date());

        session.save(bookDB);

        session.getTransaction().commit();




    }
}
