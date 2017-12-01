package com.mikhadyuk.scholarshipcalculator.action.impl;

import com.mikhadyuk.scholarshipcalculator.action.Action;
import com.mikhadyuk.scholarshipcalculator.dao.BaseDao;
import com.mikhadyuk.scholarshipcalculator.dao.impl.StudentDaoImpl;
import com.mikhadyuk.scholarshipcalculator.factory.DaoFactory;
import com.mikhadyuk.scholarshipcalculator.model.Student;
import com.mikhadyuk.scholarshipcalculator.service.ScholarshipService;
import com.mikhadyuk.scholarshipcalculator.service.StudentService;
import com.mikhadyuk.scholarshipcalculator.util.SingletonUtil;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

public class RecalculateScholarshipsAction implements Action {
    private StudentService studentService;
    private ScholarshipService scholarshipService;

    private DaoFactory daoFactory;

    private BaseDao baseDao;

    public RecalculateScholarshipsAction() {
        studentService = SingletonUtil.getInstance(StudentService.class);
        scholarshipService = SingletonUtil.getInstance(ScholarshipService.class);
        daoFactory = SingletonUtil.getInstance(DaoFactory.class);
        baseDao = daoFactory.getBaseDaoByClass(Student.class);
    }

    @Override
    public boolean doAction(ObjectOutputStream outputStream, ObjectInputStream inputStream) {
        try {
            recalculateScholarships();
            outputStream.writeObject(studentService.findAll());
        } catch (IOException e) {
            e.printStackTrace();
            return true;
        }
        return false;
    }

    private void recalculateScholarships() {
        List<Student> students = studentService.findAll();
        for (Student student : students) {
            student.setScholarshipAmount(scholarshipService.calculateScholarshipAmount(student));
            baseDao.update(student);
        }
    }
}
