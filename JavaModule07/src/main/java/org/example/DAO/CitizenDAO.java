package org.example.DAO;

import org.example.models.Citizen;
import org.example.utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class CitizenDAO {
    public Citizen findById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Citizen.class, id);
    }


    public void save(Citizen citizen) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(citizen);
        tx1.commit();
        session.close();
    }

    public void delete(Citizen citizen) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(citizen);
        tx1.commit();
        session.close();
    }

    public void update(Citizen citizen) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(citizen);
        tx1.commit();
        session.close();
    }

    public List<Citizen> findAll() {
        List<Citizen> citizens = (List<Citizen>)  HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From Citizen").list();
        return citizens;
    }
}
