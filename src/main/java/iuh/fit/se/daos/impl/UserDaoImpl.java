package iuh.fit.se.daos.impl;

import iuh.fit.se.daos.UserDAO;
import iuh.fit.se.entities.User;
import iuh.fit.se.utils.EntityManagerFactoryUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.List;

public class UserDaoImpl implements UserDAO {
    private EntityManager entityManager;
    public UserDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    @Override
    public User save(User user) {
        EntityTransaction tx = entityManager.getTransaction();
        try {
            tx.begin();
            entityManager.persist(user);
            tx.commit();
            return user;
        } catch (Exception e) {
            e.printStackTrace();
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            return null;
        }
    }

    @Override
    public User update(User user) {
        EntityTransaction tx = entityManager.getTransaction();
        try {
            tx.begin();
            entityManager.merge(user);
            tx.commit();
            return user;
        } catch (Exception e) {
            e.printStackTrace();
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            return null;
        }
    }

    @Override
    public boolean delete(int id) {
        EntityTransaction tx = entityManager.getTransaction();
        try {
            tx.begin();
            User user = entityManager.find(User.class, id);
            if (user != null) {
                entityManager.remove(user);
                tx.commit();
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            return false;
        }
    }

    @Override
    public User findById(int id) {
        EntityTransaction tx = entityManager.getTransaction();
        try {
            tx.begin();
            User user = entityManager.find(User.class, id);
            tx.commit();
            return user;
        } catch (Exception e) {
            e.printStackTrace();
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            return null;
        }
    }

    @Override
    public List<User> findAll() {
        EntityTransaction tx = entityManager.getTransaction();
        try {
            tx.begin();
            List<User> users = entityManager.createQuery("FROM User", User.class).getResultList();
            tx.commit();
            return users;
        } catch (Exception e) {
            e.printStackTrace();
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            return null;
        }
    }
}
