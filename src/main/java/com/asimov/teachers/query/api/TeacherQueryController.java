package com.asimov.teachers.query.api;


import com.asimov.teachers.query.projections.TeacherView;
import com.asimov.teachers.query.projections.TeacherViewRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.text.html.parser.Entity;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/teachers")
@Tag(name = "Teachers")
public class TeacherQueryController {
    private final TeacherViewRepository teacherViewRepository;
    public TeacherQueryController(TeacherViewRepository teacherViewRepository){
        this.teacherViewRepository = teacherViewRepository;
    }
    @GetMapping("")
    @Operation(summary = "Get all teachers")
    public ResponseEntity<List<TeacherView>> getAll(){
        try {
            return new ResponseEntity<List<TeacherView>>(teacherViewRepository.findAll(), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/id/{id}")
    @Operation(summary = "Get teacher by id")
    public ResponseEntity<TeacherView> getById(@PathVariable("id") String id){
        try {
            Optional<TeacherView> teacherViewOptional = teacherViewRepository.findById(id);
            if (teacherViewOptional.isPresent()){
                return new ResponseEntity<TeacherView>(teacherViewOptional.get(),HttpStatus.OK);
            }
            return new ResponseEntity("NOT_FOUND", HttpStatus.NOT_FOUND);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
