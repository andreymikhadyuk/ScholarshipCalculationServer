package com.mikhadyuk.scholarshipcalculator.dao.impl;

import com.mikhadyuk.scholarshipcalculator.dao.BaseDao;
import com.mikhadyuk.scholarshipcalculator.dao.FacultyDao;
import com.mikhadyuk.scholarshipcalculator.model.Faculty;
import com.mikhadyuk.scholarshipcalculator.util.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public class FacultyDaoImpl extends BaseDao<Integer, Faculty> implements FacultyDao {
    @Override
    public Faculty findById(Integer id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Faculty faculty;
        try {
            faculty = session.get(Faculty.class, id);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return faculty;
    }

    @Override
    public List<Faculty> findAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Faculty> faculties;
        try {
            faculties = session.createQuery("from Faculty").list();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return faculties;
    }

    @Override
    public Faculty update(Faculty entity) {
        super.update(entity);
        return findById(entity.getId());
    }
}
