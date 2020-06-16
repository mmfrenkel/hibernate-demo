package com.learning.hibernate.demo;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.learning.hibernate.demo.entity.Instructor;
import com.learning.hibernate.demo.entity.InstructorDetail;


public class BidirectionalDemoInstructor {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetail.class)
								.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {			
			
			// start a transaction
			session.beginTransaction();

			// get the instructor detail object
			int theId = 4;
			InstructorDetail tempInstructorDetail = 
					session.get(InstructorDetail.class, theId);
			
			System.out.println("tempInstructorDetail: " + tempInstructorDetail);
						
			System.out.println("the associated instructor: " + 
								tempInstructorDetail.getInstructor());
			
			System.out.println("Deleting tempInstructorDetail: " 
											+ tempInstructorDetail);

			 // here we delete the instructor too!
			session.delete(tempInstructorDetail); 
			
			// commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
		}
		catch (Exception exc) {
			exc.printStackTrace();
		}
		finally {
			// handle connection leak issue
			session.close();
			factory.close();
		}
	}
}
