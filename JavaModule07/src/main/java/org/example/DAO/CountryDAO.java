package org.example.DAO;

import org.example.models.Citizen;
import org.example.models.City;
import org.example.models.Country;
import org.example.utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class CountryDAO {
    public Country findById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Country.class, id);
    }


    public void save(Country country) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(country);
        tx1.commit();
        session.close();
    }

    public void delete(Country country) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(country);
        tx1.commit();
        session.close();
    }

    public void update(Country country) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(country);
        tx1.commit();
        session.close();
    }

    public List<Country> findAll() {
        List<Country> countries = (List<Country>)  HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From Country").list();
        return countries;
    }

    public Country findCountryById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Country.class, id);
    }
}
