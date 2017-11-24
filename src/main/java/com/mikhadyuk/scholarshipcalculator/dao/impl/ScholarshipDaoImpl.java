package com.mikhadyuk.scholarshipcalculator.dao.impl;

import com.mikhadyuk.scholarshipcalculator.dao.BaseDao;
import com.mikhadyuk.scholarshipcalculator.dao.ScholarshipDao;
import com.mikhadyuk.scholarshipcalculator.model.Scholarship;
import com.mikhadyuk.scholarshipcalculator.util.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public class ScholarshipDaoImpl extends BaseDao<Integer, Scholarship> implements ScholarshipDao {
    @Override
    public Scholarship findById(Integer id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Scholarship scholarship;
        try {
            scholarship = session.get(Scholarship.class, id);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return scholarship;
    }

    @Override
    public List<Scholarship> findAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Scholarship> scholarships;
        try {
            scholarships = session.createQuery("from Scholarship").list();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return scholarships;
    }

    @Override
    public Scholarship update(Scholarship entity) {
        super.update(entity);
        return findById(entity.getId());
    }
}
