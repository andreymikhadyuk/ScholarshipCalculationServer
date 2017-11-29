package com.mikhadyuk.scholarshipcalculator.factory;

import com.mikhadyuk.scholarshipcalculator.action.enums.ClassName;
import com.mikhadyuk.scholarshipcalculator.dao.BaseDao;
import com.mikhadyuk.scholarshipcalculator.dao.impl.FacultyDaoImpl;
import com.mikhadyuk.scholarshipcalculator.dao.impl.ScholarshipDaoImpl;
import com.mikhadyuk.scholarshipcalculator.dao.impl.StudentDaoImpl;
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
        }
        return null;
    }
}
