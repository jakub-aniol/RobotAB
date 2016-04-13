package com.epam.robot.Entity.DBUser.util;

import com.mkyong.user.DBUser;
import com.mkyong.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.Date;

public class CreateUser {
	
	public static void main(String[] args) {
		
		System.out.println("Creating user");
		
		// Create session factory object
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		// getting session object from session factory
		Session session = sessionFactory.openSession();
		// getting transaction object from session object
		session.beginTransaction();
		
		DBUser user = new DBUser();
		user.setUsername("DefaultUser");
		user.setCreatedBy("system");
		user.setCreatedDate(new Date());

		session.save(user);
		session.getTransaction().commit();
		sessionFactory.close();
	}
}
