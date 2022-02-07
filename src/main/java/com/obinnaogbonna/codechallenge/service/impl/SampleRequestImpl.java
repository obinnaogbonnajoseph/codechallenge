package com.obinnaogbonna.codechallenge.service.impl;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.obinnaogbonna.codechallenge.service.SampleRequest;

import org.springframework.beans.factory.annotation.Value;

public class SampleRequestImpl implements SampleRequest {

    @Value("${jdoodle.clientId}")
    private String clientId;

    @Value("${jdoodle.clientSecret}")
    private String clientSecret;

    @Override
    public void postRequest(String url) {
        System.setProperty("jasypt.encrypt.password", "salting");
        try {
            URL hp = new URL(url);
            HttpURLConnection hpCon = (HttpURLConnection) hp.openConnection();
            hpCon.setDoOutput(true);
            hpCon.setRequestMethod("POST");
            hpCon.setRequestProperty("Content-Type", "application/json");

            OutputStream out = hpCon.getOutputStream();
            out.write(getBody().getBytes());
            out.flush();

            var bufferedReader = new BufferedReader(new InputStreamReader(hpCon.getInputStream()));

            String output;
            while ((output = bufferedReader.readLine()) != null) {
                System.out.println(output);
            }
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private String getBody() {
        return String.format(""" 
            {
                "clientId": "%s",
                "clientSecret": "%s",
                "script": "%s",
                "language": "java",
                "versionIndex": "4"
            }
        """, clientId, clientSecret, getScript());
    }

    private String getScript() {
        return """
            public class MyImpl {
                public static void main(String[] args) {
                    System.out.print("Hello World");
                }}
        """
        .replaceAll("\\s", " ")
        .replaceAll("\"", "\\\\\"")
        .trim();
    }

    public static void main(String[] args) {
        new SampleRequestImpl().postRequest("https://api.jdoodle.com/v1/execute");
    } 
    
}
