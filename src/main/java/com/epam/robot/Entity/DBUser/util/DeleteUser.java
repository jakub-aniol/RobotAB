package com.epam.robot.Entity.DBUser.util;

import com.mkyong.user.DBUser;
import com.mkyong.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class DeleteUser {

	public static void main(String[] args) {
		System.out.println("Deleting users");

		// Create session factory object
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		// getting session object from session factory
		Session session = sessionFactory.openSession();
		// getting transaction object from session object
		session.beginTransaction();
		
		DBUser user = (DBUser) session.load(DBUser.class, 1);
		session.delete(user);
		System.out.println("Deleted Successfully");
		
		session.getTransaction().commit();
		sessionFactory.close();

	}
}
