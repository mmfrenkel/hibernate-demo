package com.learning.hibernate.manyToOne;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.learning.hibernate.manyToOne.entity.Course;
import com.learning.hibernate.manyToOne.entity.Instructor;
import com.learning.hibernate.manyToOne.entity.InstructorDetail;


public class CreateDemoCourses {

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
			session.beginTransaction();     
			System.out.println("Adding new courses...");

			// get an instructor
			int pkey = 1;
			Instructor instructor = session.get(Instructor.class, pkey);

			Course course1 = new Course("Gardening Tips -- Honey Bees as Pollinators");
			Course course2 = new Course("Keeping Kale Happy");

			// associate courses with instructor
			instructor.add(course1);
			instructor.add(course2);

			session.save(course1);
			session.save(course2);

			// commit transaction
			session.getTransaction().commit(); 
			System.out.println("Done!");

		} finally {
			session.close();
			factory.close();
		}
	}
}
