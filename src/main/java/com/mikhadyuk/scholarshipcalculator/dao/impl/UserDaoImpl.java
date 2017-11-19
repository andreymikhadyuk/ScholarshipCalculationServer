package com.mikhadyuk.scholarshipcalculator.dao.impl;

import com.mikhadyuk.scholarshipcalculator.dao.UserDao;
import com.mikhadyuk.scholarshipcalculator.model.User;
import com.mikhadyuk.scholarshipcalculator.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import java.util.List;

public class UserDaoImpl implements UserDao {
    @Override
    public User findById(Integer id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        return session.get(User.class, id);
    }

    @Override
    public User findByUsernameAndPassword(String username, String password) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<User> query = session.createQuery("from User where username = :username and password = :password"
                , User.class);
        query.setParameter("username", username);
        query.setParameter("password", password);
        User user = query.uniqueResult();
        if (user != null) {
            user.setPassword("");
        }
        return user;
    }

    @Override
    public List<User> findAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void save(User user) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void update(User user) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.update(user);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void delete(Integer id) {
        User user = findById(id);
        if (user != null) {
            delete(user);
        }
    }

    @Override
    public void delete(User user) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.delete(user);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
