package com.obinnaogbonna.codechallenge.service;

import com.obinnaogbonna.codechallenge.entity.Task;
import com.obinnaogbonna.codechallenge.model.TaskRequest;
import com.obinnaogbonna.codechallenge.util.RequirementNotMetException;
import com.obinnaogbonna.codechallenge.util.ResourceNotFoundException;

import java.io.IOException;
import java.util.List;

public interface TaskService {

    public List<Task> fetchAll();

    public Integer getScore(TaskRequest data) throws IOException, RequirementNotMetException, ResourceNotFoundException;
}
