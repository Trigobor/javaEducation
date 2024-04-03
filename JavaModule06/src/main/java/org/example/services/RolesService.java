package org.example.services;

import org.example.models.Role;
import org.example.models.User;
import org.example.utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.LinkedList;
import java.util.List;

public class RolesService {

    public RolesService() {
    }

    public Role findRoleByID(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Role.class, id);
    }

    public List<Role> findAllUsers() {
        return (List<Role>)  HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From Role").list();
    }

    public void addUserToRole(User user, Role role){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        user.addRole(role);
        role.addUser(user);
        //session.persist(role);
        //session.persist(user);

        transaction.commit();
        session.close();
    }
    public void addUsersToRole(Role role, List <User> users){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        for (User user : users){
            role.addUser(user);
            session.persist(user);
        }

        session.persist(role);
        transaction.commit();
        session.close();
    }

    public List<User> getUsersByRole(Role role){
        List<User> ret = new LinkedList<>();
        ret.addAll(role.getUsers());
        return ret;
    }

    public void updateRole(Role role) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        session.update(role);
        transaction.commit();

        session.close();
    }

    public void deleteRole(Role role) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        List<User> userList = this.getUsersByRole(role);
        if(!userList.isEmpty()) {
            for (User user : userList) {
                user.removeRole(role);
                role.removeUser(user);
            }
        }
        session.remove(role);
        transaction.commit();

        session.close();
    }


    public void createRoles(List <String> roleNames){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        for (String roleName : roleNames){
            Role role = new Role(null, roleName);
            session.persist(role);
        }

        transaction.commit();
        session.close();
    }
    public Role createRole(String roleName){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        Role role = new Role(null, roleName);
        session.persist(role);

        transaction.commit();
        session.close();
        return role;
    }
}
