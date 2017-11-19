package com.mikhadyuk.scholarshipcalculator.dao;

import com.mikhadyuk.scholarshipcalculator.model.User;

public interface UserDao extends BaseDao<Integer, User> {
    User findByUsernameAndPassword(String username, String password);
}
