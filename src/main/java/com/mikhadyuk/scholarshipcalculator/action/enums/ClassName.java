package com.mikhadyuk.scholarshipcalculator.action.enums;

import com.mikhadyuk.scholarshipcalculator.model.Faculty;
import com.mikhadyuk.scholarshipcalculator.model.Scholarship;
import com.mikhadyuk.scholarshipcalculator.model.ScholarshipProperty;
import com.mikhadyuk.scholarshipcalculator.model.Speciality;

import java.io.Serializable;

public enum ClassName implements Serializable{
    NONE, SCHOLARSHIP, SCHOLARSHIP_PROPERTY, FACULTY, SPECIALITY;

    public static ClassName getClassNameByClassType(Class c) {
        if (c == Scholarship.class) {
            return SCHOLARSHIP;
        } else if (c == ScholarshipProperty.class) {
            return SCHOLARSHIP_PROPERTY;
        } else if (c == Faculty.class) {
            return FACULTY;
        } else if (c == Speciality.class) {
            return SPECIALITY;
        }
        return NONE;
    }

    private static final long serialVersionUID = 2L;
}
