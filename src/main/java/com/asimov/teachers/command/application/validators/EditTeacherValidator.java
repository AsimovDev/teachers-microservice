package com.asimov.teachers.command.application.validators;

import com.asimov.common.application.Notification;
import com.asimov.teachers.command.application.dtos.request.EditTeacherRequest;
import com.asimov.teachers.command.application.dtos.request.RegisterTeacherRequest;
import com.asimov.teachers.command.domain.Teacher;
import com.asimov.teachers.command.infraestructure.TeacherRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class EditTeacherValidator {
    private final TeacherRepository teacherRepository;

    public EditTeacherValidator(TeacherRepository teacherRepository){
        this.teacherRepository = teacherRepository;
    }
    public Notification validate(EditTeacherRequest editTeacherRequest){
        Notification notification = new Notification();

        String teacherId = editTeacherRequest.getId().trim();
        if(teacherId.isEmpty()){
            notification.addError("Teacher id is required");
        }
        Optional<Teacher> customerIdOptional = teacherRepository.findById(UUID.fromString(teacherId));
        if (customerIdOptional.isPresent()) {
            notification.addError("Teacher not found");
            return notification;
        }
        String firstNane = editTeacherRequest.getFirstName() != null ? editTeacherRequest.getFirstName().trim() : "";
        if(firstNane.isEmpty()){
            notification.addError("Username is required");
        }
        String lastName = editTeacherRequest.getLastName() != null ? editTeacherRequest.getLastName().trim() : "";
        if(lastName.isEmpty()){
            notification.addError("Lastname is required");
        }
        String point = editTeacherRequest.getPoint() != null ? editTeacherRequest.getPoint().trim() : "";
        if(point.isEmpty()){
            notification.addError("Point is required");
        }
        String age = editTeacherRequest.getAge() != null ? editTeacherRequest.getAge().trim() : "";
        if(age.isEmpty()){
            notification.addError("Age is required");
        }
        String email = editTeacherRequest.getEmail() != null ? editTeacherRequest.getEmail().trim() : "";
        if(email.isEmpty()){
            notification.addError("Email is required");
        }
        String password = editTeacherRequest.getPassword() != null ? editTeacherRequest.getPassword().trim() : "";
        if(password.isEmpty()){
            notification.addError("Password is required");
        }
        String phone = editTeacherRequest.getPhone() != null ? editTeacherRequest.getPhone().trim() : "";
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
