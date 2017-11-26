package com.mikhadyuk.scholarshipcalculator.dao.impl;

import com.mikhadyuk.scholarshipcalculator.dao.BaseDao;
import com.mikhadyuk.scholarshipcalculator.dao.ScholarshipPropertyDao;
import com.mikhadyuk.scholarshipcalculator.model.ScholarshipProperty;
import com.mikhadyuk.scholarshipcalculator.util.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public class ScholarshipPropertyDaoImpl extends BaseDao<Integer, ScholarshipProperty> implements ScholarshipPropertyDao {
    @Override
    public ScholarshipProperty findById(Integer id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        ScholarshipProperty scholarshipProperty;
        try {
            scholarshipProperty = session.get(ScholarshipProperty.class, id);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return scholarshipProperty;
    }

    @Override
    public List<ScholarshipProperty> findAll() {
        throw new UnsupportedOperationException();
    }
}
