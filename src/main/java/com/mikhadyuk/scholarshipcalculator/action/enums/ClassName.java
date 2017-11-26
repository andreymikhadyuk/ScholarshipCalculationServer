package com.mikhadyuk.scholarshipcalculator.action.enums;

import com.mikhadyuk.scholarshipcalculator.model.Scholarship;
import com.mikhadyuk.scholarshipcalculator.model.ScholarshipProperty;

import java.io.Serializable;

public enum ClassName implements Serializable{
    NONE, SCHOLARSHIP, SCHOLARSHIP_PROPERTY;

    public static ClassName getClassNameByClassType(Class c) {
        if (c == Scholarship.class) {
            return SCHOLARSHIP;
        } else if (c == ScholarshipProperty.class) {
            return SCHOLARSHIP_PROPERTY;
        }
        return NONE;
    }

    private static final long serialVersionUID = 1L;
}
