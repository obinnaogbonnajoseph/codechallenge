package com.obinnaogbonna.codechallenge.service;

import com.obinnaogbonna.codechallenge.repository.TaskRepository;
import com.obinnaogbonna.codechallenge.repository.UserRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersistenceService {

    @Getter
    private TaskRepository taskRepository;

    @Getter
    private UserRepository userRepository;

    @Autowired
    private void setTaskRepository(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Autowired
    private void setUserRepository(UserRepository userRepository) { this.userRepository = userRepository;}
}
