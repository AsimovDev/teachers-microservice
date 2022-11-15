package com.asimov.teachers.command.application.validators;

import com.asimov.common.application.Notification;
import com.asimov.teachers.command.application.dtos.request.RegisterTeacherRequest;
import com.asimov.teachers.command.domain.Teacher;
import com.asimov.teachers.command.infraestructure.TeacherRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class RegisterTeacherValidator {
    private final TeacherRepository teacherRepository;

    public RegisterTeacherValidator(TeacherRepository teacherRepository){
        this.teacherRepository = teacherRepository;
    }
    public Notification validate(RegisterTeacherRequest registerTeacherRequest){
        Notification notification = new Notification();

        String firstNane = registerTeacherRequest.getFirstName() != null ? registerTeacherRequest.getFirstName().trim() : "";
        if(firstNane.isEmpty()){
            notification.addError("Username is required");
        }
        String lastName = registerTeacherRequest.getLastName() != null ? registerTeacherRequest.getLastName().trim() : "";
        if(lastName.isEmpty()){
            notification.addError("Lastname is required");
        }
        String point = registerTeacherRequest.getPoint() != null ? registerTeacherRequest.getPoint().trim() : "";
        if(point.isEmpty()){
            notification.addError("Point is required");
        }
        String age = registerTeacherRequest.getAge() != null ? registerTeacherRequest.getAge().trim() : "";
        if(age.isEmpty()){
            notification.addError("Age is required");
        }
        String email = registerTeacherRequest.getEmail() != null ? registerTeacherRequest.getEmail().trim() : "";
        if(email.isEmpty()){
            notification.addError("Email is required");
        }
        String password = registerTeacherRequest.getPassword() != null ? registerTeacherRequest.getPassword().trim() : "";
        if(password.isEmpty()){
            notification.addError("Password is required");
        }
        String phone = registerTeacherRequest.getPhone() != null ? registerTeacherRequest.getPhone().trim() : "";
        if(phone.isEmpty()){
            notification.addError("Phone is required");
        }
        if (notification.hasErrors()){
            return notification;
        }
        Optional<Teacher> emailTeacherOptional = teacherRepository.findByEmailValue(email);
        if (emailTeacherOptional.isPresent()){
            notification.addError("Teacher email is taken");
        }
        return notification;
    }
}
