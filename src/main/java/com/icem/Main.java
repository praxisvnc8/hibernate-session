package com.icem;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);
    public static void main(String[] args) {

        // 1. Create the SessionFactory (This reads hibernate.cfg.xml)
        // .addAnnotatedClass(Student.class) tells Hibernate to look for @Entity annotations
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        // 2. Create a Session (The bridge between Java and the Database)
        Session session = factory.openSession();

        // 3. Transactions are mandatory for 'write' operations in Hibernate
        Transaction transaction = null;

        try {
            // Start the operation
            transaction = session.beginTransaction();

            // Create a new Student object
            System.out.println("Creating new student object...");
            Student tempStudent = new Student();
            tempStudent.setName("Eren Jaegar");
//            tempStudent.setId(101);

            // Save the student object
            System.out.println("Saving the student to the database...");
            session.persist(tempStudent);

            // Commit the transaction (This is when data is actually sent to Postgres)
            transaction.commit();
            System.out.println("Success! Student ID: " + tempStudent.getId());

        } catch (Exception e) {
            // If anything goes wrong, undo the changes
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error("An error occurred while saving the student: ", e);
        } finally {
            // 4. Close resources to prevent memory leaks
            session.close();
            factory.close();
        }
    }
}