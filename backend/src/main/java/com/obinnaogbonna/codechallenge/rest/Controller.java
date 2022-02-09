package com.obinnaogbonna.codechallenge.rest;

import com.obinnaogbonna.codechallenge.entity.Task;
import com.obinnaogbonna.codechallenge.model.*;
import com.obinnaogbonna.codechallenge.service.TaskService;
import com.obinnaogbonna.codechallenge.service.UserService;
import com.obinnaogbonna.codechallenge.util.RequirementNotMetException;
import com.obinnaogbonna.codechallenge.util.ResourceNotFoundException;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping(value = "/task", produces = "application/json", consumes = "application/json")
public class Controller {

    @Autowired
    private UserService userService;

    @Autowired
    private TaskService taskService;

    @PostMapping()
    public ResponseEntity<?> submit(@NotNull @Valid @RequestBody RequestDto dto) throws IOException,
            ResourceNotFoundException, RequirementNotMetException {
        UserRequest userRequest = new UserRequest(dto.getUserName(), 0, Collections.emptyList());
        TaskRequest taskRequest = new TaskRequest(dto.getTaskName(), dto.getCode(), "");
        if(userService.isNewTask(userRequest.getName(), taskRequest.getName())) {
            Integer score = taskService.getScore(taskRequest);
            userRequest.setScore(score);
            userService.update(userRequest);
        }
        return new ResponseEntity<>("", HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<TaskResponse>> tasks() {

        var tasksResponse = taskService.fetchAll()
                        .stream()
                        .map(task -> {
                            return new TaskResponse(task.getName(), task.getStarterCode(), task.getDescription());
                        }).toList();
        return new ResponseEntity<>(tasksResponse, HttpStatus.OK);
    }

    @GetMapping("winners/{page}")
    public ResponseEntity<UsersResponse> winners(@PathVariable int page) {
        var usersReponse = this.userService.findAllAndSortByScore(page);
        return new ResponseEntity<>(usersReponse, HttpStatus.OK);
    }

}
