package com.mikhadyuk.scholarshipcalculator.model.enums;

import java.io.Serializable;

public enum Role implements Serializable{
    ROLE_ACCOUNTANT("Бухгалтер"), ROLE_SECRETARY("Секретарь"), ROLE_ADMIN("Админ");

    private String label;

    Role(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    @Override
    public String toString() {
        return getLabel();
    }

    private static final long serialVersionUID = 3L;
}