package com.asimov.teachers.command.api;

import com.asimov.common.api.ApiController;
import com.asimov.common.application.Notification;
import com.asimov.common.application.Result;
import com.asimov.teachers.command.application.dtos.request.EditTeacherRequest;
import com.asimov.teachers.command.application.dtos.request.RegisterTeacherRequest;
import com.asimov.teachers.command.application.dtos.response.EditTeacherResponse;
import com.asimov.teachers.command.application.dtos.response.RegisterTeacherResponse;
import com.asimov.teachers.command.application.services.TeacherApplicationService;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.command.AggregateNotFoundException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/teachers")
@Tag(name = "Teachers")
public class TeacherCommandController {
    private final TeacherApplicationService teacherApplicationService;
    private final CommandGateway commandGateway;

    public TeacherCommandController(TeacherApplicationService teacherApplicationService, CommandGateway commandGateway){
        this.teacherApplicationService = teacherApplicationService;
        this.commandGateway = commandGateway;
    }

    @PostMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> register(@RequestBody RegisterTeacherRequest registerTeacherRequest){
        try {
            Result<RegisterTeacherResponse, Notification> result = teacherApplicationService.register(registerTeacherRequest);
            if(result.isSuccess()){
                return ApiController.created(result.getSuccess());
            }
            return ApiController.error(result.getFailure().getErrors());
        } catch (Exception e){
            return ApiController.serverError();
        }

    }
    @PutMapping("/{teacherId}")
    public ResponseEntity<Object> edit(@PathVariable("teacherId") String teacherId, @RequestBody EditTeacherRequest editTeacherRequest){
        try {
            editTeacherRequest.setTeacherId(teacherId);
            Result<EditTeacherResponse, Notification> result = teacherApplicationService.edit(editTeacherRequest);
            if(result.isSuccess()){
                return ApiController.ok(result.getSuccess());
            }
            return ApiController.error(result.getFailure().getErrors());
        } catch (AggregateNotFoundException exception){
            return ApiController.notFound();
        } catch (Exception e){
            return ApiController.serverError();
        }

    }
}
