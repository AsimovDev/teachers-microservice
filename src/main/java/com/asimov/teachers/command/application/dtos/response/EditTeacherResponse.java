package com.asimov.teachers.command.application.dtos.response;

import lombok.Value;

@Value
public class EditTeacherResponse {
    private String teacherId;
    private String firstName;
    private String lastName;
    private String point;
    private String age;
    private String email;
    private String password;
    private String phone;
}
