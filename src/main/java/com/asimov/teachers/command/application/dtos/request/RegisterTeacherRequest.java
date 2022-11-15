package com.asimov.teachers.command.application.dtos.request;

import lombok.Value;

@Value
public class RegisterTeacherRequest {
    private String firstName;
    private String lastName;
    private String point;
    private String age;
    private String email;
    private String password;
    private String phone;
}
