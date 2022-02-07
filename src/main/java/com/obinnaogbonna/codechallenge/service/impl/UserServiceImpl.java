package com.obinnaogbonna.codechallenge.service.impl;

import com.obinnaogbonna.codechallenge.entity.User;
import com.obinnaogbonna.codechallenge.model.UserRequest;
import com.obinnaogbonna.codechallenge.service.PersistenceService;
import com.obinnaogbonna.codechallenge.service.UserService;
import com.obinnaogbonna.codechallenge.util.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final PersistenceService persistenceService;

    private final ModelMapper modelMapper;

    @Override
    public User update(UserRequest data) {
        var repo = persistenceService.getUserRepository();
        var optUser = Optional.ofNullable(repo.findByName(data.getName()));
        User user;
        if(optUser.isEmpty()) {
            user = new User();
            modelMapper.map(data, user);
        } else {
            user = optUser.get();
            user.setScore(data.getScore());
            user.setTasks(data.getTasks());
        }
        return repo.save(user);
    }
}
