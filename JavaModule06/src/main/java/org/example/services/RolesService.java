package org.example.services;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.example.models.Role;
import org.example.models.User;
import org.example.utils.HibernateSessionFactoryUtil;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class RolesService {

    public RolesService() {
    }

    public Role findRoleByID(int id) {
        return HibernateSessionFactoryUtil
                .getSessionFactory()
                .openSession()
                .get(Role.class, id);
    }

    public List<Role> findAllRoles() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Role> criteriaQuery = criteriaBuilder.createQuery(Role.class);

        Root<Role> rootEntry = criteriaQuery.from(Role.class);
        CriteriaQuery<Role> all = criteriaQuery.select(rootEntry);

        return session.createQuery(all).getResultList();
    }

    public void updateRole(Role role) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        Role updatingRole = session.find(Role.class, role.getId());
        if (updatingRole == null)
        {
            updatingRole = new Role(null, role.getName());
            session.persist(updatingRole);
        } else {
            updatingRole.setName(role.getName());
        }

        transaction.commit();
        session.close();
    }



    public void deleteRole(Role role) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        Role deletindRole = session.find(Role.class, role.getId());
        if (deletindRole == null)
        {
            transaction.rollback();
            session.close();
            throw new ObjectNotFoundException(role.getId(), "Role");
        }

        Set<User> users = deletindRole.getUsers();
        users.forEach(user -> user.removeRole(deletindRole));
        session.remove(deletindRole);

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
