package com.learning.hibernate.oneToOne;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.learning.hibernate.oneToOne.entity.Course;
import com.learning.hibernate.oneToOne.entity.Instructor;
import com.learning.hibernate.oneToOne.entity.InstructorDetail;
import com.learning.hibernate.oneToOne.entity.Review;


public class CreateCourseAndReviewsDemo {

	public static void main(String[] args){

		// create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class)
				.addAnnotatedClass(Review.class)
				.buildSessionFactory();

		Session session = factory.getCurrentSession();

		try { 
			session.beginTransaction();      

			// create a course
			Course course = new Course("Dance Dance Revolution -- Learn how to do Swing");
			
			// add some reviews
			course.addReview(new Review("I still can't dance!"));
			course.addReview(new Review("Best class ever, thanks so much!"));			
			course.addReview(new Review("Worst class ever. I will never take it again."));
			
			// save the course and leverage the cascading all to save reviews associated with it
			session.save(course);

			// commit transaction
			session.getTransaction().commit(); 
			System.out.println("Done!");

		} finally {
			session.close();
			factory.close();
		}
	}
}
