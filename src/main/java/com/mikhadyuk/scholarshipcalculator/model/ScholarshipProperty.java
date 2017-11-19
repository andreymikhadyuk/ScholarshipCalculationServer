package com.mikhadyuk.scholarshipcalculator.model;

import com.mikhadyuk.scholarshipcalculator.model.enums.EducationalScholarshipType;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "scholarship_property")
public class ScholarshipProperty implements Serializable{
    private static final long serialVersionUID = 4L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "scholarship_id")
    private Scholarship scholarship;

    private double amount;

    @Column(name = "educational_type")
    @Enumerated(EnumType.STRING)
    private EducationalScholarshipType educationalType;

    @Column(name = "max_average_score")
    private int maxAverageScore;

    @Column(name = "min_average_score")
    private int minAverageScore;

    @Column(name = "increase_coefficient")
    private double increaseCoefficient;

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

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public EducationalScholarshipType getEducationalType() {
        return educationalType;
    }

    public void setEducationalType(EducationalScholarshipType educationalType) {
        this.educationalType = educationalType;
    }

    public int getMaxAverageScore() {
        return maxAverageScore;
    }

    public void setMaxAverageScore(int maxAverageScore) {
        this.maxAverageScore = maxAverageScore;
    }

    public int getMinAverageScore() {
        return minAverageScore;
    }

    public void setMinAverageScore(int minAverageScore) {
        this.minAverageScore = minAverageScore;
    }

    public double getIncreaseCoefficient() {
        return increaseCoefficient;
    }

    public void setIncreaseCoefficient(double increaseCoefficient) {
        this.increaseCoefficient = increaseCoefficient;
    }
}
