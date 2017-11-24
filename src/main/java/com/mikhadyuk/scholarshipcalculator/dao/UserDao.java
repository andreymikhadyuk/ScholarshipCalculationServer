package com.mikhadyuk.scholarshipcalculator.dao;

import com.mikhadyuk.scholarshipcalculator.model.User;

public interface UserDao {
    User findByUsernameAndPassword(String username, String password);
}
