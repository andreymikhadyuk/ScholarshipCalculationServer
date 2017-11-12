package com.mikhadyuk.scholarshipcalculator.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "group_number")
    private int groupNumber;

    @ManyToOne
    @JoinColumn(name = "speciality_id")
    private Speciality speciality;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Mark> marks;

    @Column(name = "disability_Group")
    private boolean disabilityGroup;

    @Column(name = "social_scholarship")
    private boolean socialScholarship;

    @Column(name = "personal_scholarship")
    private boolean personalScholarship;

    @Column(name = "nominal_scholarship")
    private boolean nominalScholarship; //именная

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGroupNumber() {
        return groupNumber;
    }

    public void setGroupNumber(int groupNumber) {
        this.groupNumber = groupNumber;
    }

    public Speciality getSpeciality() {
        return speciality;
    }

    public void setSpeciality(Speciality speciality) {
        this.speciality = speciality;
    }

    public List<Mark> getMarks() {
        return marks;
    }

    public void setMarks(List<Mark> marks) {
        this.marks = marks;
    }

    public boolean isDisabilityGroup() {
        return disabilityGroup;
    }

    public void setDisabilityGroup(boolean disabilityGroup) {
        this.disabilityGroup = disabilityGroup;
    }

    public boolean isSocialScholarship() {
        return socialScholarship;
    }

    public void setSocialScholarship(boolean socialScholarship) {
        this.socialScholarship = socialScholarship;
    }

    public boolean isPersonalScholarship() {
        return personalScholarship;
    }

    public void setPersonalScholarship(boolean personalScholarship) {
        this.personalScholarship = personalScholarship;
    }

    public boolean isNominalScholarship() {
        return nominalScholarship;
    }

    public void setNominalScholarship(boolean nominalScholarship) {
        this.nominalScholarship = nominalScholarship;
    }
}
