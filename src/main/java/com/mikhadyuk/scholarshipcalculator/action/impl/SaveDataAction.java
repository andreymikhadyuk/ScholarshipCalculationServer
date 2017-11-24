package com.mikhadyuk.scholarshipcalculator.action.impl;

import com.mikhadyuk.scholarshipcalculator.action.Action;
import com.mikhadyuk.scholarshipcalculator.dao.BaseDao;
import com.mikhadyuk.scholarshipcalculator.dao.impl.ScholarshipDaoImpl;
import com.mikhadyuk.scholarshipcalculator.model.Scholarship;
import com.mikhadyuk.scholarshipcalculator.util.SingletonUtil;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SaveDataAction implements Action {
    private BaseDao baseDao;

    public SaveDataAction() {
        baseDao = SingletonUtil.getInstance(ScholarshipDaoImpl.class);
    }

    @Override
    public boolean doAction(ObjectOutputStream outputStream, ObjectInputStream inputStream) {
        try {
            Object objectForSaving = inputStream.readObject();
            Object object = save(objectForSaving);
            outputStream.writeObject(object);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return true;
        }
        return false;
    }

    private Object save(Object objectForSaving) {
        Object object = null;
        if (objectForSaving instanceof Scholarship) {
            object = baseDao.save((Scholarship) objectForSaving);
        }
        return object;
    }
}
