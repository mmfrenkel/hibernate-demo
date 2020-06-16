package com.learning.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.learning.hibernate.demo.entity.Student;


public class QueryStudentDemo {

	public static void main(String[] args) {
		
		// create session factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		// use the session object to do something
		try {
		    session.beginTransaction();             // begin transaction
		    
		    // display all students 
		    System.out.println("All Students in db:");
		    List<Student> students = session.createQuery("from Student").getResultList();
		    displayStudents(students);
		    
		    System.out.println("\nStudents with last name 'Doe':");
		    students =  session.createQuery("from Student s where " 
		    		+ "s.lastName = 'Doe'").getResultList();
		    displayStudents(students);
		    
		    System.out.println("\nStudents with last name 'Doe' or first name is 'Daffy:");
		    List<Student> anotherStudentSubset = session.createQuery("from Student s where "
		    		+ "s.lastName = 'Doe' OR s.firstName = 'Daffy'").getResultList();
		    displayStudents(anotherStudentSubset);

		    session.getTransaction().commit(); 
		    
		} finally {
		    factory.close();
		}
	}

	private static void displayStudents(List<Student> students) {
		for (Student oneStudent: students) {
			System.out.println(oneStudent);
		}
	}
}
