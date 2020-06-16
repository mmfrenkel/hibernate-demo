package com.learning.hibernate.demo;

import java.text.ParseException;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.learning.hibernate.demo.entity.Student;


public class ReadStudentDemo {

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
			System.out.println("Creating new student object...");
			
			Date dob = DateUtils.parseDate("11/01/1985");
		    Student tempStudent = new Student("Minnie", "Mouse", "minniemouse@disney.com", dob);
		    
		    session.beginTransaction();             // begin transaction
		    session.save(tempStudent);              // save the student
		    session.getTransaction().commit();      // commit transaction
		    
		    System.out.println("Saved new student object with ID " + tempStudent.getId());
		    
		    // now retrieve object 
		    session = factory.getCurrentSession();
		    session.beginTransaction();
		    
		    System.out.println("\nGetting student with ID: " + tempStudent.getId());
		    Student retrievedStudent = session.get(Student.class, tempStudent.getId());
		    
		    // if not found, session.get() returns null
		    session.getTransaction().commit();
		    System.out.println("Get complete: " + retrievedStudent);
		    
		} finally {
		    factory.close();
		}
	}
}
