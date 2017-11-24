package com.mikhadyuk.scholarshipcalculator.action.enums;

import com.mikhadyuk.scholarshipcalculator.model.Scholarship;

import java.io.Serializable;

public enum ClassName implements Serializable{
    NONE, SCHOLARSHIP;

    public static ClassName getClassNameByClassType(Class c) {
        if (c == Scholarship.class) {
            return SCHOLARSHIP;
        }
        return NONE;
    }

    private static final long serialVersionUID = 1L;
}
