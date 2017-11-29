package com.mikhadyuk.scholarshipcalculator.action.impl;

import com.mikhadyuk.scholarshipcalculator.action.Action;
import com.mikhadyuk.scholarshipcalculator.dao.BaseDao;
import com.mikhadyuk.scholarshipcalculator.factory.DaoFactory;
import com.mikhadyuk.scholarshipcalculator.util.SingletonUtil;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

public class GettingListOfDataAction implements Action {
    private DaoFactory daoFactory;

    private BaseDao baseDao;

    public GettingListOfDataAction() {
        daoFactory = SingletonUtil.getInstance(DaoFactory.class);
    }

    @Override
    public boolean doAction(ObjectOutputStream outputStream, ObjectInputStream inputStream) {
        Class c;
        try {
            c = (Class) inputStream.readObject();
            if (c == null) {
                return true;
            }
            List list = getListOfData(c);
            outputStream.writeObject(list);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return true;
        }
        return false;
    }

    private List getListOfData(Class c) {
        List list = null;
        baseDao = daoFactory.getBaseDaoByClass(c);
        if (list == null) {
            list = baseDao.findAll();
        }
        return list;
    }
}
