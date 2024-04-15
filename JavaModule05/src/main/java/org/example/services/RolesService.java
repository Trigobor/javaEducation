package org.example.services;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.EntityTransaction;
import org.example.models.Role;
import org.example.models.User;
import org.example.utils.DataBaseWatcher;

import java.util.List;
import java.util.Set;

public class RolesService {

    public RolesService() {
    }

    public Role findRoleByID(int id) {
        EntityManager entityManager = DataBaseWatcher.getWatcher().getEntityManagerFactory().createEntityManager();
        Role role = null;
        try {
            role = entityManager.find(Role.class, id);
            entityManager.close();
            return role;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
        return role;
    }

    public List<Role> findAllRoles() {
        EntityManager entityManager = DataBaseWatcher.getWatcher().getEntityManagerFactory().createEntityManager();
        List<Role> role = null;
        try {
            entityManager.createQuery("SELECT u FROM Role u", Role.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
        return role;
    }

    public void updateRole(Role role) {
        EntityManager entityManager = DataBaseWatcher.getWatcher().getEntityManagerFactory().createEntityManager();
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            Role updatingRole = entityManager.find(Role.class, role.getId());
            if (updatingRole == null) {
                throw new EntityNotFoundException("Role" + role.getId());
            }
            updatingRole.setName(role.getName());
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
    }


    public void deleteRole(Role role) {
        EntityManager entityManager = DataBaseWatcher.getWatcher().getEntityManagerFactory().createEntityManager();
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            ;
            Role deletingRole = entityManager.find(Role.class, role.getId());
            if (deletingRole == null) {
                throw new EntityNotFoundException("Role" + role.getId());
            }
            Set<User> users = deletingRole.getUsers();
            users.forEach(user -> user.removeRole(deletingRole));
            entityManager.remove(deletingRole);
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
    }

    public Role createRole(String roleName) {
        EntityManager entityManager = DataBaseWatcher.getWatcher().getEntityManagerFactory().createEntityManager();
        EntityTransaction transaction = null;
        Role role = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();

            role = new Role(null, roleName);
            entityManager.persist(role);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
        return role;
    }
}
