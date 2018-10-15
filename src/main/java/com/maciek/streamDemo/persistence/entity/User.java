package com.maciek.streamDemo.persistence.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "student_wykladowca")
public class User {
    @Id
    @GeneratedValue
    private Integer id_StudentWykladowca;
    @OneToMany
    private List<UserGroups> userGroups;
    private String login;
    private String password;
    @Column(name = "Nazwisko")
    private String surname;
    @Column(name = "Imie")
    private String name;
    private String email;
    @Column(name = "Nr_Indexu")
    private String indexNumber;

    public Integer getId_StudentWykladowca() {
        return id_StudentWykladowca;
    }

    public void setId_StudentWykladowca(Integer id_StudentWykladowca) {
        this.id_StudentWykladowca = id_StudentWykladowca;
    }

    public List<UserGroups> getUserGroups() {
        return userGroups;
    }

    public void setUserGroups(List<UserGroups> userGroups) {
        this.userGroups = userGroups;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIndexNumber() {
        return indexNumber;
    }

    public void setIndexNumber(String indexNumber) {
        this.indexNumber = indexNumber;
    }

    public User withEmail(String email) {
        this.email = email;
        return this;
    }

    public User withPassword(String password) {
        this.password = password;
        return this;
    }
}
