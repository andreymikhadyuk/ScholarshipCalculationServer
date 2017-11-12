package com.mikhadyuk.scholarshipcalculator.model;

import com.mikhadyuk.scholarshipcalculator.model.enums.Role;

import javax.persistence.*;

@Entity
@Table(name = "user")
@PrimaryKeyJoinColumn(name = "id")
public class User extends Person{
    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column
    @Enumerated(EnumType.STRING)
    private Role role;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
