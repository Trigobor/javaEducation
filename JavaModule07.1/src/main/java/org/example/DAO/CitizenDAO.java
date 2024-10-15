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

public class CitizenDAO {
    public Citizen findById(int id) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Query<Citizen> query = session.createQuery("from Citizen where id = :id", Citizen.class);
            query.setParameter("id", id);
            Citizen citizen = query.getSingleResult();
            if (citizen == null) {
                throw new EntityNotFoundException("City with id " + id + " not found.");
            }
            return citizen;
        }
    }

    public Citizen createCitizen(String citizenName, int cityID) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        Citizen user = null;
        try {
            transaction = session.beginTransaction();
            Query<City> query = session.createQuery("from City where id = :cityID", City.class);
            query.setParameter("cityID", cityID);
            City city = query.getSingleResult();
            user = new Citizen(citizenName, city);
            session.save(user);
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return user;
    }

    public void save(Citizen citizen) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        
        try {
            transaction = session.beginTransaction();
            session.persist(citizen);
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

    public void delete(Citizen citizen) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            Citizen deletingCitizen = session.find(Citizen.class, citizen.getId());
            if (deletingCitizen == null) {
                throw new ObjectNotFoundException(citizen.getId(), "There is no such object");
            }
            session.remove(deletingCitizen);
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

    public void update(Citizen citizen) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Citizen updatingCitizen = session.find(Citizen.class, citizen.getId());
            if (updatingCitizen == null) {
                throw new ObjectNotFoundException(citizen.getId(), "There is no such object");
            }
            session.merge(citizen);
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

    public List<Citizen> findAll() {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Query<Citizen> query = session.createQuery("from Citizen", Citizen.class);
            return query.list();
        }
    }
}
