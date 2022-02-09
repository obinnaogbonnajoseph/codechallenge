package com.obinnaogbonna.codechallenge.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TaskHttpResponse {

    private String output;

    private Integer statusCode;

    private String memory;

    private String cpuTime;

    private String error;

    private Integer used;
}
