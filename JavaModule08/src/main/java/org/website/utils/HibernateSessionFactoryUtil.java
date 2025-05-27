package org.website.utils;

import org.website.entity.Role;
import org.website.entity.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateSessionFactoryUtil {
    private static SessionFactory sessionFactory;

    private HibernateSessionFactoryUtil() {
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            synchronized (HibernateSessionFactoryUtil.class) {
                if (sessionFactory == null) {
                    try {
                        Configuration configuration = new Configuration().configure();
                        configuration.addAnnotatedClass(Role.class);
                        configuration.addAnnotatedClass(User.class);
                        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                        sessionFactory = configuration.buildSessionFactory(builder.build());
                    } catch (Exception e) {
                        System.out.println("There were some problems starting the connection: " + e);
                    }
                }
            }
        }
        return sessionFactory;
    }

    //Потом поразвлекайся с этим методом, если захочешь прочувствовать мощь синглтона
    public static void shutdown() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}