package org.website.DAO;

import org.hibernate.query.Query;
import org.website.entity.User;
import org.website.exceptions.InvalidPasswordException;
import org.website.exceptions.UserNotFoundException;
import org.website.utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UserDAO {

    public void saveUser(User user) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }


    public void updateUser(User user) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.update(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public void deleteUser(int id) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            User user = session.get(User.class, id);
            if (user != null) {
                session.delete(user);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public User getUserById(int id) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            return session.get(User.class, id);
        }
    }

    public User getUserByLoginAndPassword(String username, String password) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Query<User> query = session.createQuery("FROM User WHERE login = :username", User.class);
            query.setParameter("username", username);

            User user = query.uniqueResult();
            if (user == null) {
                throw new UserNotFoundException(username);
            }
            if (!user.getPassword().equals(password)) {
                throw new InvalidPasswordException(username);
            }
            return user;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("An error occurred during authentication: ", e);
        }
    }

    public List<User> getAllUsers() {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM User", User.class).list();
        }
    }
}
