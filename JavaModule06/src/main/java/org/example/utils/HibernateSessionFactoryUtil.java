package org.website.utils;

import org.website.models.Role;
import org.website.models.User;
import org.hibernate.*;
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
                        configuration.addAnnotatedClass(User.class);
                                                                                     configuration.addAnnotatedClass(Role.class);
                        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                        sessionFactory = configuration.buildSessionFactory(builder.build());

                    } catch (Exception e) {
                        System.out.println("sukablyad!" + e);
                    }
                }
            }
        }
        return sessionFactory;
    }
}

