package com.mikhadyuk.scholarshipcalculator.dao.impl;

import com.mikhadyuk.scholarshipcalculator.dao.BaseDao;
import com.mikhadyuk.scholarshipcalculator.dao.StudentDao;
import com.mikhadyuk.scholarshipcalculator.model.Student;
import com.mikhadyuk.scholarshipcalculator.util.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public class StudentDaoImpl extends BaseDao<Integer, Student> implements StudentDao {
    @Override
    public Student findById(Integer id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Student object;
        try {
            object = session.get(Student.class, id);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return object;
    }

    @Override
    public List<Student> findAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Student> objects;
        try {
            objects = session.createQuery("from Student").list();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return objects;
    }

    @Override
    public Student update(Student entity) {
        super.update(entity);
        return findById(entity.getId());
    }
}
