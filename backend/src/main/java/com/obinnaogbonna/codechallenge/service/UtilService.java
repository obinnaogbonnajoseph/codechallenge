package com.obinnaogbonna.codechallenge.service;

import com.obinnaogbonna.codechallenge.service.impl.TaskHttpClientImpl;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UtilService {

    @Getter
    private TaskList taskList;

    @Getter
    private TaskHttpRequest taskHttpRequest;

    @Autowired
    public void setTaskList(TaskList taskList) {
        this.taskList = taskList;
    }

    @Autowired
    public void setTaskHttpRequest(TaskHttpClientImpl taskHttpRequest) { this.taskHttpRequest = taskHttpRequest;}
}
