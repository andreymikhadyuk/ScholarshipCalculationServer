package com.mikhadyuk.scholarshipcalculator.service;

import com.mikhadyuk.scholarshipcalculator.dao.impl.BaseAmountDaoImpl;
import com.mikhadyuk.scholarshipcalculator.model.BaseAmount;
import com.mikhadyuk.scholarshipcalculator.model.ScholarshipProperty;
import com.mikhadyuk.scholarshipcalculator.util.SingletonUtil;

public class BaseAmountService {
    private BaseAmountDaoImpl baseAmountDao;

    public BaseAmountService() {
        baseAmountDao = SingletonUtil.getInstance(BaseAmountDaoImpl.class);
    }

    public double getScholarshipPropertyBaseAmount(ScholarshipProperty scholarshipProperty) {
        for (BaseAmount baseAmount : baseAmountDao.findAll()) {
            if (baseAmount.getEducationalType() == scholarshipProperty.getEducationalType()) {
                return baseAmount.getAmount();
            }
        }
        return 0.;
    }
}
