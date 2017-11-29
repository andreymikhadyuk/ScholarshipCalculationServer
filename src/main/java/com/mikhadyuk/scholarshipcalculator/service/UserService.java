package com.mikhadyuk.scholarshipcalculator.service;

import com.mikhadyuk.scholarshipcalculator.dao.impl.UserDaoImpl;
import com.mikhadyuk.scholarshipcalculator.model.User;
import com.mikhadyuk.scholarshipcalculator.util.EncoderUtil;
import com.mikhadyuk.scholarshipcalculator.util.SingletonUtil;

public class UserService {
    private UserDaoImpl userDao;

    private EncoderUtil encoder;

    public UserService() {
        userDao = SingletonUtil.getInstance(UserDaoImpl.class);
        encoder = SingletonUtil.getInstance(EncoderUtil.class);
    }

    public User parsePassword(User user) {
        if (user != null) {
            user.setPassword(encoder.encode(user.getPassword()));
        }
        return user;
    }

    public User parsePasswordWithCheck(User user) {
        if (user == null) {
            return user;
        }
        User userInDB = userDao.findById(user.getId());
        if (userInDB != null && user.getPassword().equals(userInDB.getPassword())) {
            user.setPassword(userInDB.getPassword());
        } else {
            parsePassword(user);
        }
        return user;
    }
}
