package com.luv2code.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class CreateStudentDemo {

	public static void main(String[] args) {
		//create session factory
		
		SessionFactory factory = new Configuration().
								configure("hibernate.cfg.xml")
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();
		
		//create session
		Session session = factory.getCurrentSession();
		
		try {
			
			
			
			//start the transaction
			session.beginTransaction();
			
			//query students
			List<Student> theStudents = session.createQuery("from Student").getResultList();	
			
			//display the students
			displayStudents(theStudents);
			
			//query student where lastName=cena
			theStudents = session.createQuery("from Student s where s.lastName='Cena'").getResultList();
			
			//display the student with lastName=Cena
			displayStudents(theStudents);
			
			System.out.println("Student with lastName as Cena: "+ theStudents);
			
			
			//query students with lastname as cena or firstname as ajay
		
			theStudents = session.createQuery("from Student s where s.lastName = 'Cena' OR s.firstName = 'Ajay'").getResultList();
			
			//display student with last name as cena and first name as ajay
			System.out.println("student with last name as cena and first name as ajay");
			displayStudents(theStudents);
			
			//display emails ending with gmail.com
			
			theStudents = session.createQuery("from Student s where s.email like '%gmail.com'").getResultList();
			
			//display student with email ending with gmail.com
			System.out.println("display student with email ending with gmail.com");
			displayStudents(theStudents);
			
			//commit the transaction
			session.getTransaction().commit();
			
			System.out.println("Done!!");
		}
		finally {
			factory.close();
		}

	}

	private static void displayStudents(List<Student> theStudents) {
		for(Student tempStudent: theStudents) {
			System.out.println(tempStudent);
		}
	}

}
