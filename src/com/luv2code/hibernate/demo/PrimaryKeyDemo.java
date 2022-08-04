package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class PrimaryKeyDemo {

	public static void main(String[] args){
		//create session factory
		
				SessionFactory factory = new Configuration().
										configure("hibernate.cfg.xml")
										.addAnnotatedClass(Student.class)
										.buildSessionFactory();
				
				//create session
				Session session = factory.getCurrentSession();
				
				try {
					//use the session object to save the data
					System.out.println("creating new student object");
					
					//create student object
					Student tempStudent = new Student("John", "Cena", "cenation@gmail.com");
					
					
					//start the transaction
					session.beginTransaction();
					
					//save the student object
					session.save(tempStudent);
					
					//commit the transaction
					session.getTransaction().commit();
					
					//get primary key of tempStudent
					System.out.println("Primary Key: " + tempStudent.getId());
					
					//get a new session and start transaction
					session = factory.getCurrentSession();
					session.beginTransaction();
					
					//retrieve the object from database using ID
					System.out.println("Getting student with id: " + tempStudent.getId());
					
					Student myStudent = session.get(Student.class,tempStudent.getId());
					
					System.out.println("The Studnet: " + myStudent);
					
					//commit the transaction
					session.getTransaction().commit();
					
					System.out.println("Done!!");
				}
				finally {
					factory.close();
				}

			}

}
