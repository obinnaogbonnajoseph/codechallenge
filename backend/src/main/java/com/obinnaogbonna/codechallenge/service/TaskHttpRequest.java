package com.obinnaogbonna.codechallenge.service;

import com.obinnaogbonna.codechallenge.model.TaskHttpResponse;
import com.obinnaogbonna.codechallenge.util.CodeLanguage;

import java.io.IOException;
import java.net.URISyntaxException;

public interface TaskHttpRequest {

    public TaskHttpResponse post(String script, CodeLanguage type) throws IOException, URISyntaxException, InterruptedException;

    public TaskHttpResponse creditSpent() throws IOException, URISyntaxException, InterruptedException;
    
}
