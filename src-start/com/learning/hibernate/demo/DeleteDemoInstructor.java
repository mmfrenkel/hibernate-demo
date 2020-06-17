package com.learning.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.learning.hibernate.demo.entity.Instructor;
import com.learning.hibernate.demo.entity.InstructorDetail;


public class DeleteDemoInstructor {

	public static void main(String[] args){
		
		// create session factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetail.class)
								.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		// use the session object to do something
		try { 
			
		    session.beginTransaction();             // begin transaction 
		    
		    // get instructor by primary key and delete
		    int id = 1;
		    Instructor instructor = session.get(Instructor.class, id);
		    
		    if (instructor != null) {
		    	System.out.println("Found instructor: " + instructor.getFirstName() 
		    						+ " " + instructor.getLastName());
		    	session.delete(instructor);  		// this will also delete the instructors details
		    }
		    
		    session.getTransaction().commit();      // commit transaction
		    
		} finally {
			System.out.println("Done!");
		    factory.close();
		}
	}
}
