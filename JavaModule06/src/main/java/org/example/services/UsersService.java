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

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class UsersService {

    public UsersService() {
    }

    public User findUserByID(int id) {
        return HibernateSessionFactoryUtil
                .getSessionFactory()
                .openSession()
                .get(User.class, id);
    }


    // так и не понял как и зачем делать тут hql
    // criteria API мне показалась более красивой
    // все же, есть ряд вопросов
    public List<User> findAllUsers() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);

        Root<User> rootEntry = criteriaQuery.from(User.class);
        CriteriaQuery<User> all = criteriaQuery.select(rootEntry);

        return session.createQuery(all).getResultList();
    }

    public User createUser(String userName) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        User user = new User(null, userName);
        session.persist(user);

        transaction.commit();
        session.close();
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
        Transaction transaction = session.beginTransaction();

        User assignedUser;
        try {
            assignedUser = findUserIntoBase(user, session, transaction);
        }
        catch (ObjectNotFoundException e){
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

        transaction.commit();
        session.close();
    }

    private User findUserIntoBase(User user, Session session, Transaction transaction){
        User checkingUser = session.find(User.class, user.getId());
        if (checkingUser == null)
        {
            //неуверен нужен ли тут роллбэк
            // не понял почему исключение должно быть кастомным,
            // когда ObjectNotFoundException так хорошо подходит
            // и вообще не понял зачем выбрасывать тут исключение
            // выбросил просто потому что
            transaction.rollback();
            session.close();
            throw new ObjectNotFoundException(user.getId(), "User");
        }
        return checkingUser;
    }

    public Set<Role> getRolesByUser(User user) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        // не понял нужна ли тут трансакция
        Transaction transaction = session.beginTransaction();

        User checkingUser;
        try {
            checkingUser = findUserIntoBase(user, session, transaction);
        }
        catch (ObjectNotFoundException e){
            throw new ObjectNotFoundException(user.getId(), "User");
        }

        Set<Role> rez = new HashSet<>(checkingUser.getRoles());
        transaction.commit();
        session.close();
        return rez;
    }

    public void updateUser(User user) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        User updatingUser;
        try {
            updatingUser = findUserIntoBase(user, session, transaction);
        }
        catch (ObjectNotFoundException e){
            throw new ObjectNotFoundException(user.getId(), "User");
        }

        updatingUser.setName(user.getName());

        transaction.commit();
        session.close();
    }

    public void deleteUser(User user) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        User deletingUser;
        try {
            deletingUser = findUserIntoBase(user, session, transaction);
        }
        catch (ObjectNotFoundException e){
            throw new ObjectNotFoundException(user.getId(), "User");
        }

        Set<Role> roles = deletingUser.getRoles();
        roles.forEach(role -> role.removeUser(deletingUser));
        session.remove(deletingUser);

        transaction.commit();
        session.close();
    }
}
