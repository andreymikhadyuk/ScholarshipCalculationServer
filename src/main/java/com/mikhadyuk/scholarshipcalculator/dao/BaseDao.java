package com.mikhadyuk.scholarshipcalculator.dao;

import java.util.List;

public interface BaseDao <K extends Number, T> {
    T findById(K id);
    List<T> findAll();
    void save(T entity);
    void update(T entity);
    void delete(K id);
    void delete(T entity);
}
