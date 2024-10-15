package org.example.DAO;

import jakarta.persistence.EntityNotFoundException;
import org.example.models.Citizen;
import org.example.models.City;
import org.example.models.Country;
import org.example.utils.HibernateSessionFactoryUtil;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class CityDAO {
    public City findById(int id) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Query<City> query = session.createQuery("from City where id = :id", City.class);
            query.setParameter("id", id);
            City city = query.getSingleResult();
            if (city == null) {
                throw new EntityNotFoundException("City with id " + id + " not found.");
            }
            return city;
        }
    }

    public City createCity(String cityName, int countryID) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        City city = null;
        try {
            transaction = session.beginTransaction();
            Query<Country> query = session.createQuery("from Country where id = :countryID", Country.class);
            Country country = query.setParameter("id", countryID).getSingleResult();
            city = new City(cityName, country);
            session.persist(city);
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return city;
    }

    public void save(City city) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            session.persist(city);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void delete(City city) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            City deletingCity = session.find(City.class, city.getId());
            if (deletingCity == null) {
                throw new ObjectNotFoundException(city.getId(), "There is no such object");
            }
            session.remove(deletingCity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void update(City city) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            City updatingCity = session.find(City.class, city.getId());
            if (updatingCity == null) {
                throw new ObjectNotFoundException(city.getId(), "There is no such object");
            }
            session.merge(city);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public List<City> findAll() {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Query<City> query = session.createQuery("from City", City.class);
            return query.list();
        }

    }
}
