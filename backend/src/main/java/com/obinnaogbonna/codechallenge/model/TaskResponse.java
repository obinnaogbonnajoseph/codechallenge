package com.obinnaogbonna.codechallenge.model;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class TaskResponse extends TaskRequest {

    public TaskResponse(String name, String code, String description) {
        super(name, code, description);
    }
}
