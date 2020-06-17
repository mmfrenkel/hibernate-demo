package com.learning.hibernate.manyToMany;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.learning.hibernate.manyToMany.entity.Course;
import com.learning.hibernate.manyToMany.entity.Instructor;
import com.learning.hibernate.manyToMany.entity.InstructorDetail;
import com.learning.hibernate.manyToMany.entity.Review;
import com.learning.hibernate.manyToMany.entity.Student;


public class CreateCourseAndStudentsDemo {

	public static void main(String[] args){

		// create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class)
				.addAnnotatedClass(Review.class)
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();
		
		try {			
			
			// start a transaction
			session.beginTransaction();
						
			// create a course
			Course course = new Course("Gardening 101 -- Succulents");
						
			// save the course
			System.out.println("\nSaving the course ...");
			session.save(course);
			System.out.println("Saved the course: " + course);
			
			// create the students
			Student tempStudent1 = new Student("John", "Doe", "john@luv2code.com");
			Student tempStudent2 = new Student("Mary", "Public", "mary@luv2code.com");
						
			// add students to the course
			course.addStudent(tempStudent1);
			course.addStudent(tempStudent2);
			
			// save the students
			System.out.println("\nSaving students ...");
			session.save(tempStudent1);
			session.save(tempStudent2);
			System.out.println("Saved students: " + course.getStudents());
			
			// commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
		} finally {
			session.close();
			factory.close();
		}
	}
}
