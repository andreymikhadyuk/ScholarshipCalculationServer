package com.mikhadyuk.scholarshipcalculator.dao.impl;

import com.mikhadyuk.scholarshipcalculator.dao.BaseDao;
import com.mikhadyuk.scholarshipcalculator.dao.MarkDao;
import com.mikhadyuk.scholarshipcalculator.model.Mark;
import com.mikhadyuk.scholarshipcalculator.util.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public class MarkDaoImpl extends BaseDao<Integer, Mark> implements MarkDao {
    @Override
    public Mark findById(Integer id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Mark mark;
        try {
            mark = session.get(Mark.class, id);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return mark;
    }

    @Override
    public List<Mark> findAll() {
        throw new UnsupportedOperationException();
    }
}
