package com.mikhadyuk.scholarshipcalculator.action.impl;

import com.mikhadyuk.scholarshipcalculator.action.Action;
import com.mikhadyuk.scholarshipcalculator.action.enums.ClassName;
import com.mikhadyuk.scholarshipcalculator.dao.BaseDao;
import com.mikhadyuk.scholarshipcalculator.dao.impl.FacultyDaoImpl;
import com.mikhadyuk.scholarshipcalculator.dao.impl.ScholarshipDaoImpl;
import com.mikhadyuk.scholarshipcalculator.dao.impl.ScholarshipPropertyDaoImpl;
import com.mikhadyuk.scholarshipcalculator.dao.impl.SpecialityDaoImpl;
import com.mikhadyuk.scholarshipcalculator.util.SingletonUtil;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class DeleteDataAction implements Action {
    private BaseDao baseDao;

    public DeleteDataAction() {
        baseDao = SingletonUtil.getInstance(ScholarshipDaoImpl.class);
    }

    @Override
    public boolean doAction(ObjectOutputStream outputStream, ObjectInputStream inputStream) {
        Class c;
        try {
            c = (Class) inputStream.readObject();
            if (c == null) {
                return true;
            }
            Integer id = (Integer) inputStream.readObject();
            delete(ClassName.getClassNameByClassType(c), id);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return true;
        }
        return false;
    }

    private void delete(ClassName className, Integer id) {
        switch (className) {
            case SCHOLARSHIP:
                baseDao = SingletonUtil.getInstance(ScholarshipDaoImpl.class);
                break;
            case SCHOLARSHIP_PROPERTY:
                baseDao = SingletonUtil.getInstance(ScholarshipPropertyDaoImpl.class);
                break;
            case FACULTY:
                baseDao = SingletonUtil.getInstance(FacultyDaoImpl.class);
                break;
            case SPECIALITY:
                baseDao = SingletonUtil.getInstance(SpecialityDaoImpl.class);
                break;
            default:
                return;
        }
        baseDao.delete(id);
    }
}