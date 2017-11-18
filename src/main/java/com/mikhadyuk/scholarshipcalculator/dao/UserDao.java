package com.mikhadyuk.scholarshipcalculator.dao;

import com.mikhadyuk.scholarshipcalculator.model.User;

public interface UserDao {
    void save(User user);
    void update(User user);
    void delete(User user);
}
