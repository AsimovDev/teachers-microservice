package com.asimov.teachers.query.projections;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class TeacherView {
    @Id @Column(length = 36) @Getter @Setter
    private String teacherId;
    @Column(length = 50) @Getter @Setter
    private String firstName;
    @Column(length = 50) @Getter @Setter
    private String lastName;
    @Column(length = 50) @Getter @Setter
    private String point;
    @Column(length = 50) @Getter @Setter
    private String age;
    @Column(length = 50) @Getter @Setter
    private String email;
    @Column(length = 50) @Getter @Setter
    private String password;
    @Column(length = 50) @Getter @Setter
    private String phone;

    public TeacherView(String teacherId, String firstName, String lastName, String point, String age, String email, String password, String phone){
        this.teacherId = teacherId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.point = point;
        this.age = age;
        this.email = email;
        this.password = password;
        this.phone = phone;
    }

    public TeacherView() {

    }
}
