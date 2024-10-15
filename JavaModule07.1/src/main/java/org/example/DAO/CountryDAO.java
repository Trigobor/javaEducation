package org.example.DAO;

import jakarta.persistence.EntityNotFoundException;
import org.example.models.City;
import org.example.models.Country;
import org.example.utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class CountryDAO {
    public Country findById(int id) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Query<Country> query = session.createQuery("from Country where id = :id", Country.class);
            query.setParameter("id", id);
            Country country = query.getSingleResult();
            if (country == null) {
                throw new EntityNotFoundException("City with id " + id + " not found.");
            }
            return country;
        }
    }

    public Country createCountry(String countryName) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        Country country = null;
        try {
            transaction = session.beginTransaction();
            country = new Country(countryName);
            session.persist(country);
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return country;
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
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Query<Country> query = session.createQuery("from Country", Country.class);
            return query.list();
        }

    }

}
