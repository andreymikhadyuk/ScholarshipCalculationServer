package com.mikhadyuk.scholarshipcalculator.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "scholarship_property")
public class ScholarshipProperty implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "scholarship_id")
    private Scholarship scholarship;

    @Column(name = "max_average_score")
    private double maxAverageScore;

    @Column(name = "min_average_score")
    private double minAverageScore;

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

    public double getMaxAverageScore() {
        return maxAverageScore;
    }

    public void setMaxAverageScore(double maxAverageScore) {
        this.maxAverageScore = maxAverageScore;
    }

    public double getMinAverageScore() {
        return minAverageScore;
    }

    public void setMinAverageScore(double minAverageScore) {
        this.minAverageScore = minAverageScore;
    }

    public double getIncreaseCoefficient() {
        return increaseCoefficient;
    }

    public void setIncreaseCoefficient(double increaseCoefficient) {
        this.increaseCoefficient = increaseCoefficient;
    }
}
