package com.mikhadyuk.scholarshipcalculator.dao.impl;

import com.mikhadyuk.scholarshipcalculator.dao.BaseDao;
import com.mikhadyuk.scholarshipcalculator.dao.UserDao;
import com.mikhadyuk.scholarshipcalculator.model.User;
import com.mikhadyuk.scholarshipcalculator.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import java.util.List;

public class UserDaoImpl extends BaseDao<Integer, User> implements UserDao {
    @Override
    public User findById(Integer id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        User user;
        try {
            user = session.get(User.class, id);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return user;
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
        if (session != null) {
            session.close();
        }
        return user;
    }

    @Override
    public List<User> findAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<User> objects;
        try {
            objects = session.createQuery("from User").list();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return objects;
    }

    @Override
    public User update(User entity) {
        super.update(entity);
        return findById(entity.getId());
    }
}
