package com.asimov.teachers.command.application.dtos.request;

import lombok.Getter;
import lombok.Setter;

public class EditTeacherRequest {
    private @Setter @Getter String teacherId;
    private @Getter String firstName;
    private @Getter String lastName;
    private @Getter String point;
    private @Getter String age;
    private @Getter String email;
    private @Getter String password;
    private @Getter String phone;
}
