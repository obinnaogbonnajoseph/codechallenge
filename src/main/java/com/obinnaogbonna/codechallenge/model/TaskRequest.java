package com.obinnaogbonna.codechallenge.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TaskRequest {

    private String name;

    private String code;

    private String description;
}
