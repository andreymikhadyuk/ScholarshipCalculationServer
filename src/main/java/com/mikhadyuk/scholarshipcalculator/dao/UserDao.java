package com.mikhadyuk.scholarshipcalculator.dao;

import com.mikhadyuk.scholarshipcalculator.model.User;

public interface UserDao {
    User getById(int id);
    User getByUsernameAndPassword(String username, String password);
    void save(User user);
    void update(User user);
    void delete(User user);
}
