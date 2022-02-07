package com.obinnaogbonna.codechallenge.service;

import com.obinnaogbonna.codechallenge.entity.User;
import com.obinnaogbonna.codechallenge.model.UserRequest;
import com.obinnaogbonna.codechallenge.model.UsersResponse;

import java.util.List;

public interface UserService {

    public UsersResponse findAllAndSortByScore(int page);

    public User update(UserRequest data);

    public boolean isNewTask(String username, String taskName);
}
