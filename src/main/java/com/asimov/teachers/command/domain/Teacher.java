package com.asimov.teachers.command.domain;

import com.asimov.teacherscontracts.commands.EditTeacher;
import com.asimov.teacherscontracts.commands.RegisterTeacher;
import com.asimov.teacherscontracts.events.TeacherEdited;
import com.asimov.teacherscontracts.events.TeacherRegistered;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.Instant;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;


@Aggregate
public class Teacher {

    @AggregateIdentifier
    private String teacherId;
    private String point;
    private String firstName;
    private String lastName;
    private String age;
    private String email;
    private String password;
    private String phone;

    public Teacher(){}

    @CommandHandler
    public Teacher(RegisterTeacher command){
        Instant now = Instant.now();
        apply(
                new TeacherRegistered(
                        command.getTeacherId(),
                        command.getPoint(),
                        command.getFirstName(),
                        command.getLastName(),
                        command.getAge(),
                        command.getEmail(),
                        command.getPassword(),
                        command.getPhone(),
                        now
                )
        );
    }

    @CommandHandler
    public void handle(EditTeacher command){
        Instant now = Instant.now();
        apply(
                new TeacherEdited(
                        command.getTeacherId(),
                        command.getPoint(),
                        command.getFirstName(),
                        command.getLastName(),
                        command.getAge(),
                        command.getEmail(),
                        command.getPassword(),
                        command.getPhone(),
                        now
                )
        );
    }

    @EventSourcingHandler
    protected void on(TeacherRegistered event){
        teacherId = event.getTeacherId();
        point = event.getPoint();
        firstName = event.getFirstName();
        lastName = event.getLastName();
        age = event.getAge();
        email = event.getEmail();
        password = event.getPassword();
        phone = event.getPhone();
    }

    @EventSourcingHandler
    protected void on(TeacherEdited event){
        point = event.getPoint();
        firstName = event.getFirstName();
        lastName = event.getLastName();
        age = event.getAge();
        email = event.getEmail();
        password = event.getPassword();
        phone = event.getPhone();
    }
}
