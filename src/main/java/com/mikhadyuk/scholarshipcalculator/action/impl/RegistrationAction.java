package com.mikhadyuk.scholarshipcalculator.action.impl;

import com.mikhadyuk.scholarshipcalculator.action.Action;
import com.mikhadyuk.scholarshipcalculator.dao.impl.UserDaoImpl;
import com.mikhadyuk.scholarshipcalculator.model.User;
import com.mikhadyuk.scholarshipcalculator.util.EncoderUtil;
import com.mikhadyuk.scholarshipcalculator.util.SingletonUtil;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class RegistrationAction implements Action {
    private UserDaoImpl userDao;
    private EncoderUtil encoder;

    public RegistrationAction() {
        userDao = SingletonUtil.getInstance(UserDaoImpl.class);
        encoder = SingletonUtil.getInstance(EncoderUtil.class);
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
        user.setPassword(encoder.encode(user.getPassword()));
        userDao.save(user);
        User userInDB = userDao.findByUsernameAndPassword(user.getUsername(), user.getPassword());
        try {
            outputStream.writeObject(userInDB);
        } catch (IOException e) {
            e.printStackTrace();
            return true;
        }

        return false;
    }
}
