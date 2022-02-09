package com.obinnaogbonna.codechallenge.service.impl;

import com.obinnaogbonna.codechallenge.entity.Task;
import com.obinnaogbonna.codechallenge.entity.User;
import com.obinnaogbonna.codechallenge.model.UserRequest;
import com.obinnaogbonna.codechallenge.model.UserResponse;
import com.obinnaogbonna.codechallenge.model.UsersResponse;
import com.obinnaogbonna.codechallenge.service.PersistenceService;
import com.obinnaogbonna.codechallenge.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final PersistenceService persistenceService;

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
    public User update(UserRequest data) {
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

    @Override
    public boolean isNewTask(String username, String taskName) {
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
