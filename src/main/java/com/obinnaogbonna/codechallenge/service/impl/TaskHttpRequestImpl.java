package com.obinnaogbonna.codechallenge.service.impl;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.Gson;
import com.obinnaogbonna.codechallenge.model.TaskHttpResponse;
import com.obinnaogbonna.codechallenge.service.TaskHttpRequest;

import com.obinnaogbonna.codechallenge.util.HttpMethod;
import lombok.AccessLevel;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class TaskHttpRequestImpl implements TaskHttpRequest {

    @Value("${jdoodle.clientId}")
    private String clientId;

    @Value("${jdoodle.clientSecret}")
    private String clientSecret;

    @Setter(AccessLevel.PRIVATE)
    private String url;

    private final Gson gson;

    private final Environment environment;

    @Autowired
    public TaskHttpRequestImpl(Gson gson, Environment environment) {
        this.gson = gson;
        this.environment = environment;
    }

    @PostConstruct
    private void postConstruct() {
        System.setProperty("jasypt.encryptor.password", "salting");
    }

    @Override
    public TaskHttpResponse post(String script) throws IOException {
        // setUrl
        this.setUrl(environment.getProperty("jdoodle.url"));
        // open connection
        HttpURLConnection hpCon = openConnection(HttpMethod.POST);
        // add body to post request
        writePostBody(getBody(script), hpCon);
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

    private TaskHttpResponse getOutput(HttpURLConnection hpCon) throws IOException {
        var bufferedReader = new BufferedReader(new InputStreamReader(hpCon.getInputStream()));
        String output;
        while ((output = bufferedReader.readLine()) != null) {
            System.out.println(output);
        }
        return gson.fromJson(output, TaskHttpResponse.class);
    }

    private HttpURLConnection openConnection(HttpMethod method) throws IOException {
        URL hpUrl = new URL(url);
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

    private String getBody(String script) {
        return String.format(""" 
            {
                "clientId": "%s",
                "clientSecret": "%s",
                "script": "%s",
                "language": "java",
                "versionIndex": "4"
            }
        """, clientId, clientSecret, formatScript(script));
    }

    private String getBody() {
        return String.format(""" 
            {
                "clientId": "%s",
                "clientSecret": "%s"
            }
        """, clientId, clientSecret);
    }

    private String formatScript(String script) {
        return script
            .replaceAll("\\s", " ")
            .replaceAll("\"", "\\\\\"")
            .trim();
    }

//    public static void main(String[] args) {
//        try {
//            new TaskHttpRequestImpl().post("""
//                public class MyImpl {
//                    public static void main(String[] args) {
//                        System.out.print("Hello World");
//                    }}
//            """);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
    
}
