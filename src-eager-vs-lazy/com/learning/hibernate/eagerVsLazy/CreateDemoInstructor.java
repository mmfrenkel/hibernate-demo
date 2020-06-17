package com.learning.hibernate.eagerVsLazy;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.learning.hibernate.eagerVsLazy.entity.Course;
import com.learning.hibernate.eagerVsLazy.entity.Instructor;
import com.learning.hibernate.eagerVsLazy.entity.InstructorDetail;


public class CreateDemoInstructor {

	public static void main(String[] args){

		// create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class)
				.buildSessionFactory();

		Session session = factory.getCurrentSession();

		try { 
			System.out.println("Creating a new instructor...");

			Instructor instructor = 
					new Instructor("Susan", "Public", "publicemails@emails.com");
			
			InstructorDetail instructorDetail = 
					new InstructorDetail("http://www.susanpublic.com", "Gardening");
			
			instructor.setInstructorDetail(instructorDetail);

			
			session.beginTransaction();             // begin transaction 

			System.out.println("Saving instructor...");
			session.save(instructor);				// this will automatically save the details because of cascade type

			session.getTransaction().commit();      // commit transaction
			System.out.println("Done!");

		} finally {
			session.close();
			factory.close();
		}
	}
}
