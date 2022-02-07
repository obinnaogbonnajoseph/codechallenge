package com.obinnaogbonna.codechallenge.service;

import com.obinnaogbonna.codechallenge.entity.User;
import com.obinnaogbonna.codechallenge.model.UserRequest;

public interface UserService {

    public User findByName(String name);

    public User update(UserRequest data);
}
