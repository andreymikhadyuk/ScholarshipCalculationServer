package com.mikhadyuk.scholarshipcalculator.action.impl;

import com.mikhadyuk.scholarshipcalculator.action.Action;
import com.mikhadyuk.scholarshipcalculator.dao.UserDao;
import com.mikhadyuk.scholarshipcalculator.dao.impl.UserDaoImpl;
import com.mikhadyuk.scholarshipcalculator.model.User;
import com.mikhadyuk.scholarshipcalculator.service.UserService;
import com.mikhadyuk.scholarshipcalculator.util.EncoderUtil;
import com.mikhadyuk.scholarshipcalculator.util.SingletonUtil;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class LoginAction implements Action {
    private UserDao userDao;
    private EncoderUtil encoder;
    private UserService userService;

    public LoginAction() {
        userDao = SingletonUtil.getInstance(UserDaoImpl.class);
        encoder = SingletonUtil.getInstance(EncoderUtil.class);
        userService = SingletonUtil.getInstance(UserService.class);
    }

    @Override
    public boolean doAction(ObjectOutputStream outputStream, ObjectInputStream inputStream) {
        User user;
        try {
            user = (User) inputStream.readObject();
            userService.parsePassword(user);
            User userInDB = userDao.findByUsernameAndPassword(user.getUsername(), user.getPassword());
            outputStream.writeObject(userInDB);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return true;
        }
        return false;
    }
}
