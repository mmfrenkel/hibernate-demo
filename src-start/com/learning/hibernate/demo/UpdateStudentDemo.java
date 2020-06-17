package com.learning.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.learning.hibernate.demo.entity.Student;


public class UpdateStudentDemo {

	public static void main(String[] args) {
		
		// create session factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();
		
		
		// use the session object to do something
		try { 
		    int studentId = 1;
		    
			// create session
			Session session = factory.getCurrentSession();
		    session.beginTransaction();             // begin transaction 
		    
		    // retrieve student based on the id: primary key
		    System.out.println("Getting student with id = " + studentId);
		    Student student = session.get(Student.class, studentId);
		    
		    System.out.println("Changing first name of student, originally named " + student.getFirstName());
		    student.setFirstName("Meg");
		    
		    session.getTransaction().commit();      // commit transaction 
		    
		    
		    // ---- FOR ALL STUDENTS -----
		    
		    session = factory.getCurrentSession();
		    session.beginTransaction();
		    
		    System.out.println("\nChanging email for all students to foo@bar.com...");
		    session.createQuery("update Student set email='foo@bar.com'").executeUpdate();
		    
		    session.getTransaction().commit();      // commit transaction 
		    
		    
		} finally {
		    factory.close();
		}
	}
}
