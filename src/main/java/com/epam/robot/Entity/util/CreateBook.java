/*
package com.epam.robot.Entity.util;

import com.epam.robot.Entity.DBBooks.DBBooks;
import com.epam.robot.records.Book;
import com.epam.robot.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.Date;

public class CreateBook {

    synchronized public void addingBook(Book book) {

        System.out.println("Creating Book");

        // Create session factory object
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        // getting session object from session factory
        Session session = sessionFactory.openSession();
        // getting transaction object from session object
        //session.beginTransaction();

       DBBooks bookDB = new DBBooks(book.getLibrary(), book.getTitle(), book.getType(), book.getKeyWords().toString());


       System.out.println(book.getLibrary());





        session.getTransaction().begin();

        bookDB.setCreatedDate(new Date());
        session.save(bookDB);

        session.getTransaction().commit();

        sessionFactory.close();


    }
}
*/
