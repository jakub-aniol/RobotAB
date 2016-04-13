package com.epam.robot.Entity.DBUser.util;

import com.mkyong.user.DBUser;
import com.mkyong.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class ReadUser {

	public static void main(String[] args) {
		System.out.println("Reding users");

		// Create session factory object
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		// getting session object from session factory
		Session session = sessionFactory.openSession();
		// getting transaction object from session object
		session.beginTransaction();
		
		Query query = session.createQuery("from DBUser");
		List<DBUser> users = query.list();
		for (DBUser user : users) {
			System.out.println("User Id: " + user.getUserId() + ", User Name: " + user.getUsername() + ", Created by: "
					+ user.getCreatedBy() + ", Creation date: " + user.getCreatedDate());
		}
		session.getTransaction().commit();
		sessionFactory.close();
	}

}
