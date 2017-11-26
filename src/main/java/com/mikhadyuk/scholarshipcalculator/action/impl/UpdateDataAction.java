package com.mikhadyuk.scholarshipcalculator.action.impl;

import com.mikhadyuk.scholarshipcalculator.action.Action;
import com.mikhadyuk.scholarshipcalculator.dao.BaseDao;
import com.mikhadyuk.scholarshipcalculator.dao.impl.FacultyDaoImpl;
import com.mikhadyuk.scholarshipcalculator.dao.impl.ScholarshipDaoImpl;
import com.mikhadyuk.scholarshipcalculator.model.Faculty;
import com.mikhadyuk.scholarshipcalculator.model.Scholarship;
import com.mikhadyuk.scholarshipcalculator.util.SingletonUtil;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class UpdateDataAction implements Action{
    private BaseDao baseDao;

    public UpdateDataAction() {
        baseDao = SingletonUtil.getInstance(ScholarshipDaoImpl.class);
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
        if (objectForSaving instanceof Scholarship) {
            baseDao = SingletonUtil.getInstance(ScholarshipDaoImpl.class);
        } else if (objectForSaving instanceof Faculty) {
            baseDao = SingletonUtil.getInstance(FacultyDaoImpl.class);
        }
        if (object == null) {
            object = baseDao.update(objectForSaving);
        }
        return object;
    }
}
