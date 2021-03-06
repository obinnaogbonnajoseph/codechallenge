package com.obinnaogbonna.codechallenge.service.impl;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.obinnaogbonna.codechallenge.model.TaskHttpResponse;
import com.obinnaogbonna.codechallenge.service.TaskHttpRequest;

import com.obinnaogbonna.codechallenge.util.CodeLanguage;
import com.obinnaogbonna.codechallenge.util.HttpMethod;
import com.obinnaogbonna.codechallenge.util.TaskHttp;
import lombok.AccessLevel;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class TaskHttpRequestImpl extends TaskHttp implements TaskHttpRequest {

    private final Environment environment;

    @Autowired
    public TaskHttpRequestImpl(Environment environment) {
        this.environment = environment;
    }

    @Override
    public TaskHttpResponse post(String script, CodeLanguage type) throws IOException {
        // setUrl
        this.setUrl(environment.getProperty("jdoodle.url"));
        // open connection
        HttpURLConnection hpCon = openConnection(HttpMethod.POST);
        // add body to post request
        writePostBody(getBody(script, type.getName()), hpCon);
        return getOutput(hpCon);
    }

    @Override
    public TaskHttpResponse creditSpent() throws IOException {
        // setUrl
        this.setUrl(environment.getProperty("jdoodle.creditSpentUrl"));
        // open connection
        HttpURLConnection hpCon = openConnection(HttpMethod.POST);
        // add body to post request
        writePostBody(getBody(), hpCon);
        return getOutput(hpCon);
    }

    private HttpURLConnection openConnection(HttpMethod method) throws IOException {
        URL hpUrl = new URL(getUrl());
        HttpURLConnection hpCon = (HttpURLConnection) hpUrl.openConnection();
        hpCon.setDoOutput(true);
        hpCon.setRequestMethod(method.getName());
        hpCon.setRequestProperty("Content-Type", "application/json");
        return hpCon;
    }

    private void writePostBody(String body, HttpURLConnection hpCon) throws IOException {
        OutputStream out = hpCon.getOutputStream();
        out.write(body.getBytes());
        out.flush();
    }

    private TaskHttpResponse getOutput(HttpURLConnection hpCon) throws IOException {
        BufferedReader bufferedReader = null;
        var stringBuilder = new StringBuilder();
        if(hpCon.getResponseCode() == 200) {
            bufferedReader = new BufferedReader(new InputStreamReader(hpCon.getInputStream()));
            String output;
            while ((output = bufferedReader.readLine()) != null) {
                stringBuilder.append(output);
            }
        } else {
            bufferedReader = new BufferedReader(new InputStreamReader(hpCon.getErrorStream()));
            String errorOutput;
            while ((errorOutput = bufferedReader.readLine()) != null) {
                stringBuilder.append(errorOutput);
            }
        }
        return convertStringToResponse(stringBuilder.toString());
    }
}
