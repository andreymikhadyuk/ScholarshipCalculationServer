package com.mikhadyuk.scholarshipcalculator.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "scholarship")
public class Scholarship implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(nullable = false)
    private String type;

    @OneToMany(mappedBy = "scholarship", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ScholarshipProperty> scholarshipProperties;

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

    public List<ScholarshipProperty> getScholarshipProperties() {
        return scholarshipProperties;
    }

    public void setScholarshipProperties(List<ScholarshipProperty> scholarshipProperties) {
        this.scholarshipProperties = scholarshipProperties;
    }
}
