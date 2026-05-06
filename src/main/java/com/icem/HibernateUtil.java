package com.icem;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    // We only want ONE session factory for the whole app
    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            Configuration configuration = new Configuration();

            // 1. Load the hibernate.cfg.xml file first
            configuration.configure("hibernate.cfg.xml");

            // 2. MANUALLY inject environment variables
            // This ensures `${DB_URL}` in XML is ignored in favor of the actual value
            configuration.setProperty("hibernate.connection.url", System.getenv("DB_URL"));
            configuration.setProperty("hibernate.connection.username", System.getenv("DB_USERNAME"));
            configuration.setProperty("hibernate.connection.password", System.getenv("DB_PASSWORD"));

            // 3. Build the factory
            return configuration.buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("SessionFactory creation failed: " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}