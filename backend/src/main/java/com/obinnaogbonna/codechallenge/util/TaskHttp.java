package com.obinnaogbonna.codechallenge.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.obinnaogbonna.codechallenge.model.TaskHttpResponse;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

public class TaskHttp {

    @Value("${jdoodle.clientId}")
    private String clientId;

    @Value("${jdoodle.clientSecret}")
    private String clientSecret;

    @Setter()
    @Getter
    private String url;

    public String getBody(String script) {
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

    public String getBody() {
        return String.format(""" 
            {
                "clientId": "%s",
                "clientSecret": "%s"
            }
        """, clientId, clientSecret);
    }

    public String formatScript(String script) {
        return script
                .replaceAll("\\s", " ")
                .replaceAll("\"", "\\\\\"")
                .trim();
    }

    public String convertToJsonString(String val) {
        return val.replaceAll("\"", "\\\"");
    }

    public TaskHttpResponse convertStringToResponse(String response) throws JsonProcessingException {
        String out = convertToJsonString(response);
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(out, TaskHttpResponse.class);
    }
}
