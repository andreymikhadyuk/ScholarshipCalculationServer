package com.mikhadyuk.scholarshipcalculator.action.impl;

import com.mikhadyuk.scholarshipcalculator.action.Action;
import com.mikhadyuk.scholarshipcalculator.dao.BaseDao;
import com.mikhadyuk.scholarshipcalculator.dao.impl.ScholarshipDaoImpl;
import com.mikhadyuk.scholarshipcalculator.action.enums.ClassName;
import com.mikhadyuk.scholarshipcalculator.model.Scholarship;
import com.mikhadyuk.scholarshipcalculator.util.SingletonUtil;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

public class GettingListOfDataAction implements Action {
    private BaseDao baseDao;

    public GettingListOfDataAction() {
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
            List list = getListOfData(ClassName.getClassNameByClassType(c));
            outputStream.writeObject(list);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return true;
        }
        return false;
    }

    private List getListOfData(ClassName className) {
        List list = null;
        switch (className) {
            case SCHOLARSHIP:
                List<Scholarship> scholarshipList = baseDao.findAll();
                scholarshipList.stream().map(s -> s.getBaseAmounts());
                list = scholarshipList;
                break;
        }
        return list;
    }
}
