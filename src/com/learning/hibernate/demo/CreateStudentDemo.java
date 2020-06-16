package com.learning.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.learning.hibernate.demo.entity.Student;


public class CreateStudentDemo {

	public static void main(String[] args) {
		
		// create session factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		// use the session object to do something
		try {
			System.out.println("Creating new student object...");
		    Student tempStudent = new Student("Paul", "Wall", "pwall@anemail.com");
		    
		    session.beginTransaction();             // begin transaction
		    session.save(tempStudent);              // save the student
		    session.getTransaction().commit();      // commit transaction
		    
		    System.out.println("Saved new student object... Done!");
		    
		} finally {
		    factory.close();
		}
	}
}
