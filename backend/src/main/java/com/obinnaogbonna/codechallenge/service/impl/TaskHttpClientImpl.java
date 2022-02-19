package com.obinnaogbonna.codechallenge.service.impl;


import com.obinnaogbonna.codechallenge.model.TaskHttpResponse;
import com.obinnaogbonna.codechallenge.service.TaskHttpRequest;
import com.obinnaogbonna.codechallenge.util.CodeLanguage;
import com.obinnaogbonna.codechallenge.util.TaskHttp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class TaskHttpClientImpl extends TaskHttp implements TaskHttpRequest {

    private final Environment environment;

    @Autowired
    public TaskHttpClientImpl(Environment environment) {
        this.environment = environment;
    }

    @Override
    public TaskHttpResponse post(String script, CodeLanguage type) throws IOException, URISyntaxException, InterruptedException {
        this.setUrl(environment.getProperty("jdoodle.url"));
        var request = getRequest(script, type);
        var response = getResponse(request);
        return convertStringToResponse(response.body());
    }

    @Override
    public TaskHttpResponse creditSpent() throws IOException, URISyntaxException, InterruptedException {
        this.setUrl(environment.getProperty("jdoodle.creditSpentUrl"));
        var request = getRequest();
        var response = getResponse(request);
        return convertStringToResponse(response.body());
    }

    private HttpRequest getRequest(String script, CodeLanguage type) throws URISyntaxException {
        return HttpRequest.newBuilder()
                .uri(new URI(getUrl()))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(getBody(script, type.getName())))
                .build();
    }

    private HttpRequest getRequest() throws URISyntaxException {
        return HttpRequest.newBuilder()
                .uri(new URI(getUrl()))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(getBody()))
                .build();
    }

    private HttpResponse<String> getResponse(HttpRequest request) throws IOException, InterruptedException {
        return HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
    }
}
