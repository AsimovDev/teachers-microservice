package com.asimov.teachers.command.application.services;

import com.asimov.common.application.Notification;
import com.asimov.common.application.Result;
import com.asimov.common.application.ResultType;
import com.asimov.teachers.command.application.dtos.request.EditTeacherRequest;
import com.asimov.teachers.command.application.dtos.request.RegisterTeacherRequest;
import com.asimov.teachers.command.application.dtos.response.EditTeacherResponse;
import com.asimov.teachers.command.application.dtos.response.RegisterTeacherResponse;
import com.asimov.teachers.command.application.validators.EditTeacherValidator;
import com.asimov.teachers.command.application.validators.RegisterTeacherValidator;
import com.asimov.teacherscontracts.commands.EditTeacher;
import com.asimov.teacherscontracts.commands.RegisterTeacher;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Component
public class TeacherApplicationService {
    private final CommandGateway commandGateway;
    private final QueryGateway queryGateway;
    private final RegisterTeacherValidator registerTeacherValidator;
    private final EditTeacherValidator editTeacherValidator;

    public TeacherApplicationService(CommandGateway commandGateway, QueryGateway queryGateway, RegisterTeacherValidator registerTeacherValidator, EditTeacherValidator editTeacherValidator){
        this.commandGateway = commandGateway;
        this.queryGateway = queryGateway;
        this.registerTeacherValidator = registerTeacherValidator;
        this.editTeacherValidator = editTeacherValidator;
    }

    public Result<RegisterTeacherResponse, Notification> register(RegisterTeacherRequest registerTeacherRequest) throws Exception{
        Notification notification = this.registerTeacherValidator.validate(registerTeacherRequest);
        if (notification.hasErrors()){
            return Result.failure(notification);
        }
        String teacherId = UUID.randomUUID().toString();
        RegisterTeacher registerTeacher = new RegisterTeacher(
                teacherId,
                registerTeacherRequest.getFirstName().trim(),
                registerTeacherRequest.getLastName().trim(),
                registerTeacherRequest.getPoint().trim(),
                registerTeacherRequest.getAge().trim(),
                registerTeacherRequest.getEmail().trim(),
                registerTeacherRequest.getPassword().trim(),
                registerTeacherRequest.getPhone()
        );
        CompletableFuture<Object> future = commandGateway.send(registerTeacher);
        CompletableFuture<ResultType> futureResult = future.handle((ok, ex) -> (ex != null) ? ResultType.FAILURE : ResultType.SUCCESS);
        ResultType resultType = futureResult.get();
        if(resultType == ResultType.FAILURE){
            throw new Exception();
        }
        RegisterTeacherResponse registerTeacherResponse = new RegisterTeacherResponse(
                registerTeacher.getTeacherId(),
                registerTeacher.getFirstName(),
                registerTeacher.getLastName(),
                registerTeacher.getPoint(),
                registerTeacher.getAge(),
                registerTeacher.getEmail(),
                registerTeacher.getPassword(),
                registerTeacher.getPhone()
        );
        return Result.success(registerTeacherResponse);
    }

    public Result<EditTeacherResponse, Notification> edit(EditTeacherRequest editTeacherRequest) throws Exception{
        Notification notification = this.editTeacherValidator.validate(editTeacherRequest);
        if (notification.hasErrors()){
            return Result.failure(notification);
        }
        String teacherId = UUID.randomUUID().toString();
        EditTeacher editTeacher = new EditTeacher(
                teacherId,
                editTeacherRequest.getFirstName().trim(),
                editTeacherRequest.getLastName().trim(),
                editTeacherRequest.getPoint().trim(),
                editTeacherRequest.getAge().trim(),
                editTeacherRequest.getEmail().trim(),
                editTeacherRequest.getPassword().trim(),
                editTeacherRequest.getPhone()
        );
        CompletableFuture<Object> future = commandGateway.send(editTeacher);
        CompletableFuture<ResultType> futureResult = future.handle((ok, ex) -> (ex != null) ? ResultType.FAILURE : ResultType.SUCCESS);
        ResultType resultType = futureResult.get();
        if(resultType == ResultType.FAILURE){
            throw new Exception();
        }
        EditTeacherResponse editTeacherResponse = new EditTeacherResponse(
                editTeacher.getTeacherId(),
                editTeacher.getFirstName(),
                editTeacher.getLastName(),
                editTeacher.getPoint(),
                editTeacher.getAge(),
                editTeacher.getEmail(),
                editTeacher.getPassword(),
                editTeacher.getPhone()
        );
        return Result.success(editTeacherResponse);
    }
}
