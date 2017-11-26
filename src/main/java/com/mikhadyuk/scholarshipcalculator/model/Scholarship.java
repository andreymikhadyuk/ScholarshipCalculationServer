package com.mikhadyuk.scholarshipcalculator.model;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "scholarship")
public class Scholarship implements Serializable{
    private static final long serialVersionUID = 2L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private boolean educational;

    @OneToMany(mappedBy = "scholarship", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<ScholarshipProperty> scholarshipProperties;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "scholarship", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<BaseAmount> baseAmounts;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isEducational() {
        return educational;
    }

    public void setEducational(boolean educational) {
        this.educational = educational;
    }

    public List<ScholarshipProperty> getScholarshipProperties() {
        return scholarshipProperties;
    }

    public void setScholarshipProperties(List<ScholarshipProperty> scholarshipProperties) {
        this.scholarshipProperties = scholarshipProperties;
    }

    public List<BaseAmount> getBaseAmounts() {
        return baseAmounts;
    }

    public void setBaseAmounts(List<BaseAmount> baseAmounts) {
        this.baseAmounts = baseAmounts;
    }
}
