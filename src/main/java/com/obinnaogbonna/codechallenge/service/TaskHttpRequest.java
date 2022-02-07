package com.obinnaogbonna.codechallenge.service;

import com.obinnaogbonna.codechallenge.model.TaskHttpResponse;

import java.io.IOException;

public interface TaskHttpRequest {

    public TaskHttpResponse post(String script) throws IOException;

    public TaskHttpResponse creditSpent() throws IOException;
    
}
