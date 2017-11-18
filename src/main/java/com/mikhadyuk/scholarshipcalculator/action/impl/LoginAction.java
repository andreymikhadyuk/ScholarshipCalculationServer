package com.mikhadyuk.scholarshipcalculator.action.impl;

import com.mikhadyuk.scholarshipcalculator.action.Action;
import com.mikhadyuk.scholarshipcalculator.model.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class LoginAction implements Action {
    @Override
    public boolean doAction(ObjectOutputStream outputStream, ObjectInputStream inputStream) {

        return false;
    }
}
