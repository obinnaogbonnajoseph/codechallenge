package com.obinnaogbonna.codechallenge.service.impl;

import com.obinnaogbonna.codechallenge.entity.Task;
import com.obinnaogbonna.codechallenge.entity.User;
import com.obinnaogbonna.codechallenge.model.*;
import com.obinnaogbonna.codechallenge.service.PersistenceService;
import com.obinnaogbonna.codechallenge.service.TaskService;
import com.obinnaogbonna.codechallenge.service.UserService;
import com.obinnaogbonna.codechallenge.util.RequirementNotMetException;
import com.obinnaogbonna.codechallenge.util.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final PersistenceService persistenceService;

    @Autowired
    public TaskService taskService;

    private final ModelMapper modelMapper;

    private static final int PAGE_SIZE = 10;

    @Autowired
    public UserServiceImpl(PersistenceService persistenceService, ModelMapper modelMapper) {
        this.persistenceService = persistenceService;
        this.modelMapper = modelMapper;
    }

    @Override
    public UsersResponse findAllAndSortByScore(int page) {
        var repo = persistenceService.getUserRepository();
        var users =  repo
                .findAll(PageRequest.of((page - 1), PAGE_SIZE, Sort.by("score").descending()))
                .stream()
                .map(user -> {
                    UserResponse userResponse = new UserResponse();
                    modelMapper.map(user, userResponse);
                    return userResponse;
                }).toList();
        return new UsersResponse(users, (int)repo.count());
    }

    @Override
    public void processSubmission(RequestDto dto) throws IOException, URISyntaxException, ResourceNotFoundException, InterruptedException, RequirementNotMetException {
        UserRequest userRequest = new UserRequest(dto.getUserName(), 0, Collections.emptyList());
        TaskRequest taskRequest = new TaskRequest(dto.getTaskName(), dto.getType(), dto.getCode(), "");
        if(isNewTask(userRequest.getName(), taskRequest.getName())) {
            Integer score = taskService.getScore(taskRequest);
            Task task = taskService.findByNameAndType(dto.getTaskName(), dto.getType());
            userRequest.setTasks(Collections.singletonList(task));
            userRequest.setScore(score);
            update(userRequest);
        }
    }

    private User update(UserRequest data) {
        var repo = persistenceService.getUserRepository();
        var optUser = Optional.ofNullable(repo.findByName(data.getName()));
        User user;
        if(optUser.isEmpty()) {
            user = new User();
            modelMapper.map(data, user);
        } else {
            user = optUser.get();
            user.setScore(user.getScore() + data.getScore());
            List<Task> updatedTasks = user.getTasks();
            updatedTasks.addAll(data.getTasks());
            user.setTasks(updatedTasks);
        }
        return repo.save(user);
    }

    private boolean isNewTask(String username, String taskName) {
        var repo = persistenceService.getUserRepository();
        boolean isNewUser = Optional.ofNullable(repo.findByName(username)).isEmpty();
        if(!isNewUser) {
            return repo
                    .findByName(username)
                    .getTasks().stream()
                    .noneMatch(task -> task.getName().equals(taskName));
        }
        return true;
    }
}
