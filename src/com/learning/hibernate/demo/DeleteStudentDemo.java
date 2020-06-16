package com.learning.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.learning.hibernate.demo.entity.Student;


public class DeleteStudentDemo {

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
		    
		    System.out.println("Deleting student with first name, " + student.getFirstName());
		    session.delete(student);
		    
		    session.getTransaction().commit();      // commit transaction 
		    
		    
		    // ---- FOR ALL STUDENTS -----
		    
		    session = factory.getCurrentSession();
		    session.beginTransaction();
		    
		    System.out.println("\nDeleting student with id = 2...");
		    session.createQuery("delete from Student s where s.id = 2").executeUpdate();
		    
		    session.getTransaction().commit();      // commit transaction 
		    
		    
		} finally {
		    factory.close();
		}
	}
}
