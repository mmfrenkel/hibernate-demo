package com.learning.hibernate.eagerVsLazy;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.learning.hibernate.eagerVsLazy.entity.Course;
import com.learning.hibernate.eagerVsLazy.entity.Instructor;
import com.learning.hibernate.eagerVsLazy.entity.InstructorDetail;


public class EagerLazyDemo {

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

			// use hibernate query with HQL to get the courses
			int pkey = 1; 
			
			// here we are joining tables together and getting all the data at once
			Query<Instructor> query = session.createQuery(
					"select i from Instructor i " + "JOIN FETCH i.courses where i.id=:instructorId", 
					Instructor.class
			);
			query.setParameter("instructorId", pkey);
			
			Instructor instructor = query.getSingleResult();
			System.out.println("* Instructor: " + instructor); 
			System.out.println("* Courses: " + instructor.getCourses()); 
			
			// commit transaction
			session.getTransaction().commit(); 
			System.out.println("Done!");

		} finally {
			session.close();
			factory.close();
		}
	}
}
