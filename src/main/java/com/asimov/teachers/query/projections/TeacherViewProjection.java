package com.asimov.teachers.query.projections;

import com.asimov.teacherscontracts.events.TeacherEdited;
import com.asimov.teacherscontracts.events.TeacherRegistered;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.Timestamp;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Optional;

@Component
public class TeacherViewProjection {
    private final TeacherViewRepository teacherViewRepository;

    public TeacherViewProjection(TeacherViewRepository teacherViewRepository){
        this.teacherViewRepository = teacherViewRepository;
    }

    @EventHandler
    public void on(TeacherRegistered event, @Timestamp Instant timestamp){
        TeacherView teacherView = new TeacherView(event.getTeacherId(), event.getFirstName(), event.getLastName(), event.getPoint(), event.getAge(), event.getEmail(), event.getPassword(), event.getPhone());
        teacherViewRepository.save(teacherView);
    }

    @EventHandler
    public void on(TeacherEdited event, @Timestamp Instant timestamp){
        Optional<TeacherView> teacherViewOptional = teacherViewRepository.findById(event.getTeacherId().toString());
        if(teacherViewOptional.isPresent()){
            TeacherView teacherView = teacherViewOptional.get();
            teacherView.setFirstName(event.getFirstName());
            teacherView.setLastName(event.getLastName());
            teacherView.setPoint(event.getPoint());
            teacherView.setAge(event.getAge());
            teacherView.setEmail(event.getEmail());
            teacherView.setPassword(event.getPassword());
            teacherView.setPhone(event.getPhone());
            teacherViewRepository.save(teacherView);
        }
    }
}
