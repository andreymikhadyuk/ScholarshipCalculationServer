package com.mikhadyuk.scholarshipcalculator.model;

import com.mikhadyuk.scholarshipcalculator.model.enums.EducationalScholarshipType;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "base_amount")
public class BaseAmount implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "scholarship_id")
    private Scholarship scholarship;

    @Column(name = "educational_type")
    @Enumerated(EnumType.STRING)
    private EducationalScholarshipType educationalType;

    private double amount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Scholarship getScholarship() {
        return scholarship;
    }

    public void setScholarship(Scholarship scholarship) {
        this.scholarship = scholarship;
    }

    public EducationalScholarshipType getEducationalType() {
        return educationalType;
    }

    public void setEducationalType(EducationalScholarshipType educationalType) {
        this.educationalType = educationalType;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
