package org.example.utils;

import org.example.models.Citizen;
import org.example.models.City;
import org.example.models.Country;
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
                        configuration.addAnnotatedClass(Country.class);
                        configuration.addAnnotatedClass(City.class);
                        configuration.addAnnotatedClass(Citizen.class);
                        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                        sessionFactory = configuration.buildSessionFactory(builder.build());

                    } catch (Exception e) {
                        System.out.println("Some troubles with opening connection" + e);
                    }
                }
            }
        }
        return sessionFactory;
    }
}

