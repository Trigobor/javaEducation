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

import java.util.List;
import java.util.Set;

public class RolesService {

    public RolesService() {
    }

    public Role findRoleByID(int id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Role role = null;
        try {
            session.get(Role.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return role;
    }

    public List<Role> findAllRoles() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        List<Role> roles = null;
        try {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Role> criteriaQuery = criteriaBuilder.createQuery(Role.class);

            Root<Role> rootEntry = criteriaQuery.from(Role.class);
            CriteriaQuery<Role> all = criteriaQuery.select(rootEntry);

            roles = session.createQuery(all).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return roles;
    }

    public void updateRole(Role role) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Role updatingRole = session.find(Role.class, role.getId());
            if (updatingRole == null) {
                throw new ObjectNotFoundException(role.getId(), "Role");
            }
            updatingRole.setName(role.getName());
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }


    public void deleteRole(Role role) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Role deletingRole = session.find(Role.class, role.getId());
            if (deletingRole == null) {
                throw new ObjectNotFoundException(role.getId(), "Role");
            }
            Set<User> users = deletingRole.getUsers();
            users.forEach(user -> user.removeRole(deletingRole));
            session.remove(deletingRole);
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public Role createRole(String roleName) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        Role role = null;//Это лишнее
        try {
            transaction = session.beginTransaction();
            role = new Role(null, roleName);
            session.persist(role);
            //проверь транзакции на коммиты
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();//логирование в общем-то нужно
        } finally {
            session.close();
        }
        return role;
    }
}
