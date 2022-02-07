package com.obinnaogbonna.codechallenge.service;

import com.obinnaogbonna.codechallenge.entity.Task;
import com.obinnaogbonna.codechallenge.model.TaskRequest;
import com.obinnaogbonna.codechallenge.util.RequirementNotMetException;
import com.obinnaogbonna.codechallenge.util.ResourceNotFoundException;

import java.io.IOException;

public interface TaskService {

    public boolean isCorrect(TaskRequest data) throws IOException, RequirementNotMetException, ResourceNotFoundException;
}
