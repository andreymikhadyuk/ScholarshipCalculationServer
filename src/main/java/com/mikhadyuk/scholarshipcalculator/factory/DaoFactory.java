package com.mikhadyuk.scholarshipcalculator.factory;

import com.mikhadyuk.scholarshipcalculator.action.enums.ClassName;
import com.mikhadyuk.scholarshipcalculator.dao.BaseDao;
import com.mikhadyuk.scholarshipcalculator.dao.impl.*;
import com.mikhadyuk.scholarshipcalculator.util.SingletonUtil;

public class DaoFactory {
    public BaseDao getBaseDaoByClass(Class c) {
        switch (ClassName.getClassNameByClassType(c)) {
            case SCHOLARSHIP:
                return SingletonUtil.getInstance(ScholarshipDaoImpl.class);
            case FACULTY:
                return SingletonUtil.getInstance(FacultyDaoImpl.class);
            case STUDENT:
                return SingletonUtil.getInstance(StudentDaoImpl.class);
            case USER:
                return SingletonUtil.getInstance(UserDaoImpl.class);
            case BASE_AMOUNT:
                return SingletonUtil.getInstance(BaseAmountDaoImpl.class);
        }
        return null;
    }
}
