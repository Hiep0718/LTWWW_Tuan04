package iuh.fit.se.daos.impl;

import iuh.fit.se.daos.UserDAO;
import iuh.fit.se.entities.User;
import iuh.fit.se.utils.EntityManagerFactoryUtil;
import jakarta.persistence.EntityTransaction;

import java.util.List;

public class UserDaoImpl implements UserDAO {
    @Override
    public User save(User user) {
        EntityTransaction tx = EntityManagerFactoryUtil.getEntityManager().getTransaction();
        try {
            tx.begin();
            EntityManagerFactoryUtil.getEntityManager().persist(user);
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
        EntityTransaction tx = EntityManagerFactoryUtil.getEntityManager().getTransaction();
        try {
            tx.begin();
            EntityManagerFactoryUtil.getEntityManager().merge(user);
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
        EntityTransaction tx = EntityManagerFactoryUtil.getEntityManager().getTransaction();
        try {
            tx.begin();
            User user = EntityManagerFactoryUtil.getEntityManager().find(User.class, id);
            if (user != null) {
                EntityManagerFactoryUtil.getEntityManager().remove(user);
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
        EntityTransaction tx = EntityManagerFactoryUtil.getEntityManager().getTransaction();
        try {
            tx.begin();
            User user = EntityManagerFactoryUtil.getEntityManager().find(User.class, id);
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
        EntityTransaction tx = EntityManagerFactoryUtil.getEntityManager().getTransaction();
        try {
            tx.begin();
            List<User> users = EntityManagerFactoryUtil.getEntityManager().createQuery("FROM User", User.class).getResultList();
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
