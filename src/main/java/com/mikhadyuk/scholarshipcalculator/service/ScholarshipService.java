package com.mikhadyuk.scholarshipcalculator.service;

import com.mikhadyuk.scholarshipcalculator.dao.impl.BaseAmountDaoImpl;
import com.mikhadyuk.scholarshipcalculator.dao.impl.ScholarshipDaoImpl;
import com.mikhadyuk.scholarshipcalculator.model.BaseAmount;
import com.mikhadyuk.scholarshipcalculator.model.Scholarship;
import com.mikhadyuk.scholarshipcalculator.model.Student;
import com.mikhadyuk.scholarshipcalculator.util.SingletonUtil;

import java.util.List;

public class ScholarshipService {
    private BaseAmountDaoImpl baseAmountDao;
    private ScholarshipDaoImpl scholarshipDao;

    public ScholarshipService() {
        baseAmountDao = SingletonUtil.getInstance(BaseAmountDaoImpl.class);
        scholarshipDao = SingletonUtil.getInstance(ScholarshipDaoImpl.class);
    }

    public double calculateScholarshipAmount(Student student) {
        double scholarshipAmount = 0.;
        List<BaseAmount> baseAmounts = baseAmountDao.findAll();
        for (Scholarship scholarship : student.getScholarships()) {
            scholarshipAmount += scholarship.isEducational()
                    ? calculateEducationalScholarship(scholarship, student, baseAmounts)
                    : calculateNonEducationalScholarship(scholarship);
        }
        return scholarshipAmount;
    }

    private double calculateEducationalScholarship(Scholarship scholarship, Student student, List<BaseAmount> baseAmounts) {
        double scholarshipAmount = 0.;
        double baseScholarshipAmount = baseAmounts.stream()
                .filter(a -> a.getEducationalType() == student.getSpeciality().getEducationalScholarshipType())
                .findFirst().map(BaseAmount::getAmount).orElse(0.);
        scholarshipAmount = scholarship.getScholarshipProperties().stream()
                .filter(p -> student.getAverageScore() >= p.getMinAverageScore()
                        && student.getAverageScore() < p.getMaxAverageScore())
                .findFirst().map(s -> baseScholarshipAmount * s.getIncreaseCoefficient()).orElse(0.);
        if (student.isHandicapped()) {
            scholarshipAmount *= 1.5;
        }
        return scholarshipAmount;
    }

    private double calculateNonEducationalScholarship(Scholarship scholarship) {
        return scholarship.getScholarshipProperties().get(0).getAmount();
    }

    public List<Scholarship> findAll() {
        return scholarshipDao.findAll();
    }
}
