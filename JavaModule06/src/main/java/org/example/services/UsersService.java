package org.example.services;
import org.example.models.Role;
import org.example.models.User;
import org.example.dao.UserDAO;
import org.example.utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.LinkedList;
import java.util.List;

public class UsersService {

    public UsersService() {
    }

    public User findUserByID(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(User.class, id);
    }

    public List<User> findAllUsers() {
        return (List<User>)  HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From User").list();
    }

    public User createUser(String userName){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        User user = new User(null, userName);
        session.persist(user);

        transaction.commit();
        session.close();
        return user;
    }

    public void createUsers(List <String> userNames){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        for (String userName : userNames){
            User user = new User(null, userName);
            //session.persist(user);
        }

        //transaction.commit();
        session.close();
    }

    public void addRoleToUser(User user, Role role){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        user.addRole(role);
        role.addUser(user);
        //session.persist(role);
        //session.persist(user);

        transaction.commit();
        session.close();
    }
    public void addRolesToUser(User user, List <Role> roles){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        for (Role role : roles){
            user.addRole(role);
            //session.persist(role);
        }

        //session.persist(user);
        transaction.commit();
        session.close();
    }

    public List<Role> getRolesByUser(User user){
        List<Role> ret = new LinkedList<>();
        ret.addAll(user.getRoles());
        return ret;
    }

    public void updateUser(User user) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        session.update(user);
        transaction.commit();

        session.close();
    }

    public void deleteUser(User user) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        session.remove(user);
        transaction.commit();

        session.close();
    }
}
