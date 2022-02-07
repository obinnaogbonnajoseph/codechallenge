package com.obinnaogbonna.codechallenge.service.impl;

import com.obinnaogbonna.codechallenge.entity.Task;
import com.obinnaogbonna.codechallenge.model.TaskRequest;
import com.obinnaogbonna.codechallenge.service.PersistenceService;
import com.obinnaogbonna.codechallenge.service.TaskService;
import com.obinnaogbonna.codechallenge.service.UtilService;
import com.obinnaogbonna.codechallenge.util.RequirementNotMetException;
import com.obinnaogbonna.codechallenge.util.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final UtilService utilService;

    private final PersistenceService persistenceService;

    @Override
    public boolean isCorrect(TaskRequest data) throws IOException, RequirementNotMetException, ResourceNotFoundException {
        var taskRequest = this.utilService.getTaskHttpRequest();
        var creditSpent = taskRequest.creditSpent();
        if(creditSpent.getUsed() != null) {
            if(creditSpent.getUsed() >= 200)
                throw new RequirementNotMetException("Free credit use exceeded");
            var answer = Optional.of(persistenceService.getTaskRepository().findByName(data.getName()))
                    .orElseThrow(() -> new ResourceNotFoundException("Task does not exist")).getAnswer();
            var codeExecResponse = taskRequest.post(data.getCode());
            if(codeExecResponse.getStatusCode() == 200)
                return codeExecResponse.getOutput().trim().equals(answer);
            else throw new RequirementNotMetException(codeExecResponse.getError());
        }
        return false;
    }
}
