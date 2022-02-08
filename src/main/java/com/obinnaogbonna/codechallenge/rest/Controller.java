package com.obinnaogbonna.codechallenge.rest;

import com.obinnaogbonna.codechallenge.entity.Task;
import com.obinnaogbonna.codechallenge.entity.User;
import com.obinnaogbonna.codechallenge.model.RequestDto;
import com.obinnaogbonna.codechallenge.model.TaskRequest;
import com.obinnaogbonna.codechallenge.model.UserRequest;
import com.obinnaogbonna.codechallenge.model.UsersResponse;
import com.obinnaogbonna.codechallenge.service.TaskService;
import com.obinnaogbonna.codechallenge.service.UserService;
import com.obinnaogbonna.codechallenge.util.RequirementNotMetException;
import com.obinnaogbonna.codechallenge.util.ResourceNotFoundException;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping(value = "/task", produces = "application/json", consumes = "application/json")
public class Controller {

    private UserService userService;

    private TaskService taskService;

    @Autowired
    public Controller(UserService userService, TaskService taskService) {
        this.userService = userService;
        this.taskService = taskService;
    }

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
        return ResponseEntity.ok("");
    }

    @GetMapping()
    public ResponseEntity<List<Task>> tasks() {
        return ResponseEntity.ok(taskService.fetchAll());
    }

    @GetMapping("winners/{page}")
    public ResponseEntity<UsersResponse> winners(@PathVariable int page) {
        return ResponseEntity.ok(this.userService.findAllAndSortByScore(page));
    }

}
