package com.mikhadyuk.scholarshipcalculator.model;

import com.mikhadyuk.scholarshipcalculator.model.enums.EducationalScholarshipType;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "speciality")
public class Speciality implements Serializable{
    private static final long serialVersionUID = 2L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "speciality_name", nullable = false)
    private String specialityName;

    @Column(name = "short_speciality_name", nullable = false)
    private String shortSpecialityName;

    @ManyToOne
    @JoinColumn(name = "faculty_id", nullable = false)
    private Faculty faculty;

    @Column(name = "educational_scholarship_type")
    @Enumerated(EnumType.STRING)
    private EducationalScholarshipType educationalScholarshipType;

    @OneToMany(mappedBy = "speciality", fetch = FetchType.LAZY)
    private List<Student> students;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSpecialityName() {
        return specialityName;
    }

    public void setSpecialityName(String specialityName) {
        this.specialityName = specialityName;
    }

    public String getShortSpecialityName() {
        return shortSpecialityName;
    }

    public void setShortSpecialityName(String shortSpecialityName) {
        this.shortSpecialityName = shortSpecialityName;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public EducationalScholarshipType getEducationalScholarshipType() {
        return educationalScholarshipType;
    }

    public void setEducationalScholarshipType(EducationalScholarshipType educationalScholarshipType) {
        this.educationalScholarshipType = educationalScholarshipType;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}
