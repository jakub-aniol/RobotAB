package com.epam.robot.util;

import com.epam.robot.records.Book;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

	private static final SessionFactory sessionFactory = buildSessionFactory();
	private static Session session;

	private static SessionFactory buildSessionFactory() {
		try {
			// For XML mapping
			// return new Configuration().configure().buildSessionFactory();

			// For Annotation - wczesniej bylo to AnnotationConfiguration, ale
			// od Hibernate 3.6 jest deprecated i funkcjonalnosci przeniesione
			// do Configuration
			return new Configuration().configure().buildSessionFactory();

		} catch (Throwable ex) {

			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	private static void initializeSession() {
		session = sessionFactory.getCurrentSession();
	}

	public static void shutdown() {
		// Close caches and connection pools
		getSessionFactory().close();
	}

	public static void commit(Book bookDB){
		session.save(bookDB);
		session.getTransaction().commit();
	}

}