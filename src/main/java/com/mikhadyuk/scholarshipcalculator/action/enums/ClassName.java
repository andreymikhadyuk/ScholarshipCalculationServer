package com.mikhadyuk.scholarshipcalculator.action.enums;

import com.mikhadyuk.scholarshipcalculator.model.*;

import java.io.Serializable;

public enum ClassName implements Serializable{
    NONE, SCHOLARSHIP, SCHOLARSHIP_PROPERTY, FACULTY, SPECIALITY, STUDENT, MARK;

    public static ClassName getClassNameByClassType(Class c) {
        if (c == Scholarship.class) {
            return SCHOLARSHIP;
        } else if (c == ScholarshipProperty.class) {
            return SCHOLARSHIP_PROPERTY;
        } else if (c == Faculty.class) {
            return FACULTY;
        } else if (c == Speciality.class) {
            return SPECIALITY;
        } else if (c == Student.class) {
            return STUDENT;
        } else if (c == Mark.class) {
            return MARK;
        }
        return NONE;
    }

    private static final long serialVersionUID = 2L;
}
