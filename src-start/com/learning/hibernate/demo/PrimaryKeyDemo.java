package com.learning.hibernate.demo;

import java.text.ParseException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.learning.hibernate.demo.entity.Student;


public class PrimaryKeyDemo {

	public static void main(String[] args) throws ParseException {

		// create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();

		// use the session object to do something
		try {
			System.out.println("Creating new student objects..."); 

			// some other new student objects 
			Student student1 = new Student("Bonita", "Applebaum", "applebaum@ab.com", 
					DateUtils.parseDate("01/10/1990"));
			Student student2 = new Student("Jon", "Doe", "jondoe@doeandco.com",
					DateUtils.parseDate("14/03/1997"));
			Student student3 = new Student("Mary", "Public", "marypublic@public.com", 
					DateUtils.parseDate("02/02/1981"));

			session.beginTransaction();             // begin transaction
			session.save(student1);                 // save the students
			session.save(student2);                 // save the students
			session.save(student3);                 // save the students
			session.getTransaction().commit();      // commit transaction

			System.out.println("Saved new student objects... Done!");

		} finally {
			factory.close();
		}
	}
}
