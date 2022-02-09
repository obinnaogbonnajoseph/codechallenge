package com.obinnaogbonna.codechallenge.model;

import com.obinnaogbonna.codechallenge.entity.User;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
public class UserResponse {

    private long id;

    private String name;

    private int score;

    private List<TaskResponse> tasks;

}
