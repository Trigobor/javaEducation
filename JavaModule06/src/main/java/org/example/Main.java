package org.example;

import org.example.models.Role;
import org.example.models.Test;
import org.example.models.User;
import org.example.utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class Main {
    public static void main(String[] args) {
        SessionFactory sessionFactorySingletone = HibernateSessionFactoryUtil.getSessionFactory();
        Session session = sessionFactorySingletone.openSession();
        Transaction transaction = session.beginTransaction();

        User broodmother = new User("broodmother");
        Role woodman = new Role("woodman");
        broodmother.addRole(woodman);
        /*Role Woodman = new Role("woodman");
        Woodman.setId(10);
        User Lion = new User("lion");
        Lion.setId(10);*/

        /*session.merge(Woodman);
        session.merge(Lion);

        Role halfSupp = session.get(Role.class, 0);
        Lion.addRole(halfSupp);
        User legCommander = session.get(User.class, 3);
        legCommander.addRole(Woodman);*/
        //User legCommander = session.get(User.class, 3);
        //Role halfSupp = session.get(Role.class, 0);
        //legCommander.addRole(halfSupp);
        //session.remove(halfSupp);
        //User wisp = new User("Wisp");
        //wisp.setId(12);
        //session.persist(wisp);

        Test test = new Test(null, "basdf");
        session.persist(test);

        //session.flush();
        transaction.commit();
        session.close();
    }
}