package com.obinnaogbonna.codechallenge.service;

import com.obinnaogbonna.codechallenge.entity.Task;
import com.obinnaogbonna.codechallenge.model.TaskRequest;

public interface TaskService {

    public Task findByDescription(String description);

    public boolean isCorrect(TaskRequest data);
}
