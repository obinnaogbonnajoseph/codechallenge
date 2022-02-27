package com.obinnaogbonna.codechallenge.rest;

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
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping(value = "/task", produces = "application/json", consumes = "application/json")
public class Controller {

    private final UserService userService;

    private final TaskService taskService;

    @Autowired
    public Controller(UserService userService, TaskService taskService) {
        this.userService = userService;
        this.taskService = taskService;
    }

    @PostMapping("submit")
    public ResponseEntity<?> submit(@NotNull @Valid @RequestBody RequestDto dto) throws IOException,
            ResourceNotFoundException, RequirementNotMetException, URISyntaxException, InterruptedException {
        userService.processSubmission(dto);
        return new ResponseEntity<>("", HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<TaskResponse>> tasks() {

        var tasksResponse = taskService.fetchAll()
                        .stream()
                        .map(task ->
                                new TaskResponse.TaskResponseBuilder(task).build())
                .toList();
        return new ResponseEntity<>(tasksResponse, HttpStatus.OK);
    }

    @GetMapping("winners/{page}")
    public ResponseEntity<UsersResponse> winners(@PathVariable int page) {
        var usersResponse = this.userService.findAllAndSortByScore(page);
        return new ResponseEntity<>(usersResponse, HttpStatus.OK);
    }

}
