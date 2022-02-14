package com.obinnaogbonna.codechallenge.service.impl;

import com.obinnaogbonna.codechallenge.entity.Task;
import com.obinnaogbonna.codechallenge.model.TaskHttpResponse;
import com.obinnaogbonna.codechallenge.model.TaskRequest;
import com.obinnaogbonna.codechallenge.service.PersistenceService;
import com.obinnaogbonna.codechallenge.service.TaskService;
import com.obinnaogbonna.codechallenge.service.UtilService;
import com.obinnaogbonna.codechallenge.util.RequirementNotMetException;
import com.obinnaogbonna.codechallenge.util.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {

    private final UtilService utilService;

    private final PersistenceService persistenceService;

    @Autowired
    public TaskServiceImpl(UtilService utilService, PersistenceService persistenceService) {
        this.persistenceService = persistenceService;
        this.utilService = utilService;
    }

    @Override
    public List<Task> fetchAll() {
        return persistenceService
                .getTaskRepository()
                .findAll()
                .stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    @Override
    public Task findByName(String name) throws ResourceNotFoundException {
        return Optional.ofNullable(persistenceService.getTaskRepository()
                .findByName(name))
                .orElseThrow(() -> new ResourceNotFoundException("Task does not exist"));
    }

    @Override
    public Integer getScore(TaskRequest data) throws IOException, RequirementNotMetException, ResourceNotFoundException, URISyntaxException, InterruptedException {
        var taskRequest = this.utilService.getTaskHttpRequest();
        var answer = Optional.of(persistenceService.getTaskRepository().findByName(data.getName()))
                .orElseThrow(() -> new ResourceNotFoundException("Task does not exist")).getAnswer();
        var codeExecResponse = taskRequest.post(data.getCode());
        if(codeExecResponse.getStatusCode() == 200)
            return calculateScore(answer, codeExecResponse);
        else throw new RequirementNotMetException(codeExecResponse.getError());
    }

    private Integer calculateScore(String answer, TaskHttpResponse response) {
        int score = 0;
        if(answer.equals(response.getOutput().trim())) {
            score = 20;
            // add extra points for speed of processing;
            if(Double.parseDouble(response.getCpuTime()) <= 0.05) {
                score += 5;
            }
        }
        return score;
    }
}
