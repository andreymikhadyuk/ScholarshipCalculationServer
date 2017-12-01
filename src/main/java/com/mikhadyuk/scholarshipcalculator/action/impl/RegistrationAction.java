package com.mikhadyuk.scholarshipcalculator.action.impl;

import com.mikhadyuk.scholarshipcalculator.action.Action;
import com.mikhadyuk.scholarshipcalculator.dao.impl.UserDaoImpl;
import com.mikhadyuk.scholarshipcalculator.model.User;
import com.mikhadyuk.scholarshipcalculator.service.UserService;
import com.mikhadyuk.scholarshipcalculator.util.SingletonUtil;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class RegistrationAction implements Action {
    private UserDaoImpl userDao;
    private UserService userService;

    public RegistrationAction() {
        userDao = SingletonUtil.getInstance(UserDaoImpl.class);
        userService = SingletonUtil.getInstance(UserService.class);
    }

    @Override
    public boolean doAction(ObjectOutputStream outputStream, ObjectInputStream inputStream) {
        User user;
        try {
            user = (User) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return true;
        }
        userService.parsePassword(user);
        User updatedUser = userDao.save(user);
        try {
            outputStream.writeObject(updatedUser);
        } catch (IOException e) {
            e.printStackTrace();
            return true;
        }

        return false;
    }
}
