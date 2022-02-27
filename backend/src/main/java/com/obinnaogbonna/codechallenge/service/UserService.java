package com.obinnaogbonna.codechallenge.service;

import com.obinnaogbonna.codechallenge.model.RequestDto;
import com.obinnaogbonna.codechallenge.model.UsersResponse;
import com.obinnaogbonna.codechallenge.util.RequirementNotMetException;
import com.obinnaogbonna.codechallenge.util.ResourceNotFoundException;

import java.io.IOException;
import java.net.URISyntaxException;

public interface UserService {

    public UsersResponse findAllAndSortByScore(int page);

    public void processSubmission(RequestDto dto) throws IOException, URISyntaxException, ResourceNotFoundException, InterruptedException, RequirementNotMetException;
}
