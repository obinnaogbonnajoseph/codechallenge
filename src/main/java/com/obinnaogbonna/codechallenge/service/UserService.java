package com.obinnaogbonna.codechallenge.service;

import com.obinnaogbonna.codechallenge.entity.User;
import com.obinnaogbonna.codechallenge.model.UserRequest;
import com.obinnaogbonna.codechallenge.util.ResourceNotFoundException;

public interface UserService {

    public User update(UserRequest data);
}
