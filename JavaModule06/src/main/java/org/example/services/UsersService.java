package org.website.services;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.website.models.Role;
import org.website.models.User;
import org.website.utils.HibernateSessionFactoryUtil;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UsersService {

    public UsersService() {
    }

    public User findUserByID(int id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        User user = null;
        try {
            session.get(User.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return user;
    }


    // так и не понял как и зачем делать тут hql
    // criteria API мне показалась более красивой
    // все же, есть ряд вопросов
    public List<User> findAllUsers() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        List<User> users = null;
        try {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);

            Root<User> rootEntry = criteriaQuery.from(User.class);
            CriteriaQuery<User> all = criteriaQuery.select(rootEntry);

            users = session.createQuery(all).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return users;
    }

    public User createUser(String userName) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        User user = null;
        try {
            transaction = session.beginTransaction();
            user = new User(null, userName);
            session.persist(user);
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

    // я сделал вместо 3-х однотипных методов в 2-х классах 1 большой и красивый
    // вместо "добавить роль юзеру", "добавить роли юзеру", "добавить юзера ролям"
    // я сделал 1 единственный метод, который добавляет 1 и более ролей юзеру и убрал его
    // в юзерсервис, так как это счел логичным
    // так же теперь этот метол проверяет существовании роли и юзера и в случае чего
    // создает первое, или бросается исключениями
    public void addRoleToUser(User user, Role... roles) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            User assignedUser = session.find(User.class, user.getId());
            if (assignedUser == null) {
                throw new ObjectNotFoundException(user.getId(), "User");
            }
            for (Role role : roles) {
                Role assignedRole = session.find(Role.class, role.getId());
                if (assignedRole == null) {
                    assignedRole = new Role(null, role.getName());
                    session.persist(assignedRole);
                }
                assignedUser.addRole(assignedRole);
                assignedRole.addUser(assignedUser);
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public Set<Role> getRolesByUser(User user) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        Set<Role> roles = null;
        try {
            transaction = session.beginTransaction();
            User checkingUser = session.find(User.class, user.getId());
            if (checkingUser == null) {
                throw new ObjectNotFoundException(user.getId(), "User");
            }
            roles = new HashSet<>(checkingUser.getRoles());
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return roles;
    }

    public void updateUser(User user) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            User updatingUser = session.find(User.class, user.getId());
            if (updatingUser == null) {
                throw new ObjectNotFoundException(user.getId(), "User");
            }
            updatingUser.setName(user.getName());
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void deleteUser(User user) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            User deletingUser = session.find(User.class, user.getId());
            if (deletingUser == null) {
                throw new ObjectNotFoundException(user.getId(), "User");
            }
            Set<Role> roles = deletingUser.getRoles();
            roles.forEach(role -> role.removeUser(deletingUser));
            session.remove(deletingUser);
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
