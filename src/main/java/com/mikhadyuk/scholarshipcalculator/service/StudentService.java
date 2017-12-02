package com.mikhadyuk.scholarshipcalculator.service;

import com.mikhadyuk.scholarshipcalculator.dao.impl.ScholarshipDaoImpl;
import com.mikhadyuk.scholarshipcalculator.dao.impl.StudentDaoImpl;
import com.mikhadyuk.scholarshipcalculator.model.Scholarship;
import com.mikhadyuk.scholarshipcalculator.model.Student;
import com.mikhadyuk.scholarshipcalculator.util.SingletonUtil;

import java.util.ArrayList;
import java.util.List;

public class StudentService {
    private StudentDaoImpl studentDao;
    private ScholarshipDaoImpl scholarshipDao;

    public StudentService() {
        this.studentDao = SingletonUtil.getInstance(StudentDaoImpl.class);
        this.scholarshipDao = SingletonUtil.getInstance(ScholarshipDaoImpl.class);
    }

    public void setStudentInFields(Student studentInFields) {
        setStudentInMarks(studentInFields);
        setStudentInScholarships(studentInFields);
    }

    private void setStudentInMarks(Student student) {
        student.getMarks().forEach(s -> s.setStudent(student));
    }

    private void setStudentInScholarships(Student student) {
        List<Scholarship> scholarships = new ArrayList<>();
        student.getScholarships().forEach(s -> scholarships.add(scholarshipDao.findById(s.getId())));
        scholarships.forEach(s -> {
            if (!(s.getStudents().stream()
                    .mapToInt(Student::getId)
                    .anyMatch(id -> id == s.getId())))
            s.getStudents().add(student);
        });
    }

    public List<Student> findAll() {
        return studentDao.findAll();
    }
}
