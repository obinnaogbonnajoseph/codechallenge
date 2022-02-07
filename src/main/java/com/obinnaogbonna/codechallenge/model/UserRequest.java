package com.obinnaogbonna.codechallenge.model;

import com.obinnaogbonna.codechallenge.entity.Task;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class UserRequest {

    private String name;

    private Integer score;

    private List<Task> tasks;
}
