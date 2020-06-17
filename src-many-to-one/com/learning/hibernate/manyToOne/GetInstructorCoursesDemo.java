package com.learning.hibernate.manyToOne;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.learning.hibernate.manyToOne.entity.Course;
import com.learning.hibernate.manyToOne.entity.Instructor;
import com.learning.hibernate.manyToOne.entity.InstructorDetail;


public class GetInstructorCoursesDemo {

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
			System.out.println("Getting instructor courses...");

			// get an instructor
			int pkey = 1;
			Instructor instructor = session.get(Instructor.class, pkey);

			System.out.println("Courses: " + instructor.getCourses());

			// commit transaction
			session.getTransaction().commit(); 
			System.out.println("Done!");

		} finally {
			session.close();
			factory.close();
		}
	}
}
