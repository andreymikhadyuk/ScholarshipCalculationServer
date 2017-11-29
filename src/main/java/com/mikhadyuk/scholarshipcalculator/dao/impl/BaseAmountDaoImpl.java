package com.mikhadyuk.scholarshipcalculator.dao.impl;

import com.mikhadyuk.scholarshipcalculator.dao.BaseAmountDao;
import com.mikhadyuk.scholarshipcalculator.dao.BaseDao;
import com.mikhadyuk.scholarshipcalculator.model.BaseAmount;
import com.mikhadyuk.scholarshipcalculator.util.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public class BaseAmountDaoImpl extends BaseDao<Integer, BaseAmount> implements BaseAmountDao {
    @Override
    public BaseAmount findById(Integer id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        BaseAmount object;
        try {
            object = session.get(BaseAmount.class, id);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return object;
    }

    @Override
    public List<BaseAmount> findAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<BaseAmount> objects;
        try {
            objects = session.createQuery("from BaseAmount").list();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return objects;
    }

    @Override
    public BaseAmount update(BaseAmount entity) {
        super.update(entity);
        return findById(entity.getId());
    }
}
