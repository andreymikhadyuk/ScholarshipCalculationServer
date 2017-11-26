package com.mikhadyuk.scholarshipcalculator.dao.impl;

import com.mikhadyuk.scholarshipcalculator.dao.BaseDao;
import com.mikhadyuk.scholarshipcalculator.dao.SpecialityDao;
import com.mikhadyuk.scholarshipcalculator.model.Speciality;
import com.mikhadyuk.scholarshipcalculator.util.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public class SpecialityDaoImpl extends BaseDao<Integer, Speciality> implements SpecialityDao {
    @Override
    public Speciality findById(Integer id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Speciality speciality;
        try {
            speciality = session.get(Speciality.class, id);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return speciality;
    }

    @Override
    public List<Speciality> findAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Speciality> specialities;
        try {
            specialities = session.createQuery("from Speciality").list();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return specialities;
    }

    @Override
    public Speciality update(Speciality entity) {
        super.update(entity);
        return findById(entity.getId());
    }
}
