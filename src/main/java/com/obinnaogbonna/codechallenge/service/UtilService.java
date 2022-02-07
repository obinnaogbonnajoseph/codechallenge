package com.obinnaogbonna.codechallenge.service;

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
    private void setTaskList(TaskList taskList) {
        this.taskList = taskList;
    }

    @Autowired
    private void setTaskHttpRequest(TaskHttpRequest taskHttpRequest) { this.taskHttpRequest = taskHttpRequest;}
}
