package com.mikhadyuk.scholarshipcalculator.action.impl;

import com.mikhadyuk.scholarshipcalculator.action.Action;
import com.mikhadyuk.scholarshipcalculator.dao.BaseDao;
import com.mikhadyuk.scholarshipcalculator.factory.DaoFactory;
import com.mikhadyuk.scholarshipcalculator.model.Student;
import com.mikhadyuk.scholarshipcalculator.model.User;
import com.mikhadyuk.scholarshipcalculator.service.MarkService;
import com.mikhadyuk.scholarshipcalculator.service.ScholarshipService;
import com.mikhadyuk.scholarshipcalculator.service.StudentService;
import com.mikhadyuk.scholarshipcalculator.service.UserService;
import com.mikhadyuk.scholarshipcalculator.util.SingletonUtil;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class UpdateDataAction implements Action{
    private DaoFactory daoFactory;
    private MarkService markService;
    private StudentService studentService;
    private ScholarshipService scholarshipService;
    private UserService userService;

    private BaseDao baseDao;

    public UpdateDataAction() {
        daoFactory = SingletonUtil.getInstance(DaoFactory.class);
        markService = SingletonUtil.getInstance(MarkService.class);
        studentService = SingletonUtil.getInstance(StudentService.class);
        scholarshipService = SingletonUtil.getInstance(ScholarshipService.class);
        userService = SingletonUtil.getInstance(UserService.class);
    }

    @Override
    public boolean doAction(ObjectOutputStream outputStream, ObjectInputStream inputStream) {
        try {
            Object objectForSaving = inputStream.readObject();
            Object object = update(objectForSaving);
            outputStream.writeObject(object);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return true;
        }
        return false;
    }

    private Object update(Object objectForSaving) {
        Object object = null;
        baseDao = daoFactory.getBaseDaoByClass(objectForSaving.getClass());
        if (objectForSaving instanceof Student) {
            Student student = (Student) objectForSaving;
            studentService.setStudentInFields(student);
            student.setAverageScore(markService.calculateAverageScore(student.getMarks()));
            student.setScholarshipAmount(scholarshipService.calculateScholarshipAmount(student));
        } else if (objectForSaving instanceof User) {
            userService.parsePasswordWithCheck((User) objectForSaving);
        }
        if (object == null) {
            object = baseDao.update(objectForSaving);
        }
        return object;
    }
}
