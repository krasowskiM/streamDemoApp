package com.maciek.streamDemo.persistence.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "student_groups")
public class UserGroups {
    @Id
    @GeneratedValue
    private Integer idStudentGroup;
    @Column(name = "RokRozpoczecia")
    private Date initDate;
    @Column(name = "Nazwa_StudentGrups")
    private String name;

    public Integer getIdStudentGroup() {
        return idStudentGroup;
    }

    public void setIdStudentGroup(Integer idStudentGroup) {
        this.idStudentGroup = idStudentGroup;
    }

    public Date getInitDate() {
        return initDate;
    }

    public void setInitDate(Date initDate) {
        this.initDate = initDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
