package com.obinnaogbonna.codechallenge.model;

import com.obinnaogbonna.codechallenge.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsersResponse {

    private List<UserResponse> users;

    private int total;
}
