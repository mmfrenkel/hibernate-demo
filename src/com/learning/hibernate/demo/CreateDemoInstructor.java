package com.learning.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.learning.hibernate.demo.entity.Instructor;
import com.learning.hibernate.demo.entity.InstructorDetail;


public class CreateDemoInstructor {

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
			
			// create objects and associate them
			System.out.println("Creating a new instructor...");
			
			Instructor instructor = 
					new Instructor("Granty", "Apples", "megscoolemail@emails.com");
			InstructorDetail instructorDetail = 
					new InstructorDetail("http://www.afakeyoutubechannel.com", "Programming!");
			instructor.setInstructorDetail(instructorDetail);
			
		    session.beginTransaction();             // begin transaction 
		    
		    System.out.println("Saving instructor...");
		    session.save(instructor);				// this will automatically save the details because of cascade type
		    
		    session.getTransaction().commit();      // commit transaction
		    System.out.println("Done!");
		    
		} finally {
		    factory.close();
		}
	}
}
