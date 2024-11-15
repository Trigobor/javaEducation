package org.website.services;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.EntityTransaction;
import org.website.models.Role;
import org.website.models.User;
import org.website.utils.DataBaseWatcher;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UsersService {

    public UsersService() {
    }

    public User findUserByID(int id) {
        EntityManager entityManager = DataBaseWatcher.getWatcher().getEntityManagerFactory().createEntityManager();
        User user = null;
        try {
            user = entityManager.find(User.class, id);
            entityManager.close();
            return user;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
        return user;
    }

    public List<User> findAllUsers() {
        EntityManager entityManager = DataBaseWatcher.getWatcher().getEntityManagerFactory().createEntityManager();
        List<User> users = null;
        try {
            entityManager.createQuery("SELECT u FROM User u", User.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
        return users;
    }

    // я сделал вместо 3-х однотипных методов в 2-х классах 1 большой и красивый
    // вместо "добавить роль юзеру", "добавить роли юзеру", "добавить юзера ролям"
    // я сделал 1 единственный метод, который добавляет 1 и более ролей юзеру и убрал его
    // в юзерсервис, так как это счел логичным
    // так же теперь этот метол проверяет существовании роли и юзера и в случае чего
    // создает первое, или бросается исключениями
    public void addRoleToUser(User user, Role... roles) {
        EntityManager entityManager = DataBaseWatcher.getWatcher().getEntityManagerFactory().createEntityManager();
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            User assignedUser = entityManager.find(User.class, user.getId());
            if (assignedUser == null) {
                throw new EntityNotFoundException("User" + user.getId());
            }
            for (Role role : roles) {
                Role assignedRole = entityManager.find(Role.class, role.getId());
                if (assignedRole == null) {
                    assignedRole = new Role(null, role.getName());
                    entityManager.persist(assignedRole);
                }
                assignedUser.addRole(assignedRole);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
    }

    public Set<Role> getRolesByUser(User user) {
        EntityManager entityManager = DataBaseWatcher.getWatcher().getEntityManagerFactory().createEntityManager();
        EntityTransaction transaction = null;
        Set<Role> roles = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            User checkingUser = entityManager.find(User.class, user.getId());
            if (checkingUser == null) {
                throw new EntityNotFoundException("User" + user.getId());
            }
            roles = new HashSet<>(checkingUser.getRoles());
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
        return roles;
    }

    public void updateUser(User user) {
        EntityManager entityManager = DataBaseWatcher.getWatcher().getEntityManagerFactory().createEntityManager();
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            User updatingUser = entityManager.find(User.class, user.getId());
            if (updatingUser == null) {
                throw new EntityNotFoundException("User" + user.getId());
            }
            updatingUser.setName(user.getName());
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
    }

    public void deleteUser(User user) {
        EntityManager entityManager = DataBaseWatcher.getWatcher().getEntityManagerFactory().createEntityManager();
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            User deletingUser = entityManager.find(User.class, user.getId());
            if (deletingUser == null) {
                throw new EntityNotFoundException("User" + user.getId());
            }
            Set<Role> roles = deletingUser.getRoles();
            roles.forEach(role -> role.removeUser(deletingUser));
            entityManager.remove(deletingUser);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
    }

    public User createUser(String userName) {
        EntityManager entityManager = DataBaseWatcher.getWatcher().getEntityManagerFactory().createEntityManager();
        EntityTransaction transaction = null;
        User user = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();

            user = new User(null, userName);
            entityManager.persist(user);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
        return user;
    }
}
