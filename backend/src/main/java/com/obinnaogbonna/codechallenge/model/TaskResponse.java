package com.obinnaogbonna.codechallenge.model;

import com.obinnaogbonna.codechallenge.util.CodeLanguage;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class TaskResponse extends TaskRequest {

    public TaskResponse(String name, CodeLanguage type, String code, String description) {
        super(name, type, code, description);
    }
}
