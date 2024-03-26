package org.example;

import org.example.models.Role;
import org.example.models.User;
import org.example.utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class Main {
    public static void main(String[] args) {
        SessionFactory sessionFactorySingletone = HibernateSessionFactoryUtil.getSessionFactory();
        Session session = sessionFactorySingletone.openSession();

        Role Woodman = new Role("woodman");
        User Lion = new User("lion");
        session.persist(Woodman);
        session.persist(Lion);
        Role halfSupp = session.get(Role.class, 0);
        Lion.addRole(halfSupp);
        User legCommander = session.get(User.class, 3);
        legCommander.addRole(Woodman);
        session.flush();
    }
}