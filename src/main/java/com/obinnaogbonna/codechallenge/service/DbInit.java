package com.obinnaogbonna.codechallenge.service;

import com.obinnaogbonna.codechallenge.entity.Task;
import com.obinnaogbonna.codechallenge.repository.TaskRepository;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class DbInit {

    private PersistenceService persistenceService;

    private UtilService utilService;

    DbInit(PersistenceService persistenceService, UtilService utilService) {
        this.persistenceService = persistenceService;
        this.utilService = utilService;
    }

    @PostConstruct
    private void postConstruct() {
        List<Task> tasks = this.utilService
                .getTaskList()
                .getTasks()
                .stream()
                .map(task -> {
                    Task newTask = new Task();
                    newTask.setStarterCode(task.getStarterCode());
                    newTask.setDescription(task.getDescription());
                    newTask.setAnswer(task.getAnswer());
                    newTask.setName(task.getName());
                    return newTask;
                }).toList();
        TaskRepository repo = this.persistenceService.getTaskRepository();
        tasks.forEach(task -> {
            var optTask = Optional.ofNullable(repo.findByDescription(task.getDescription()));
            if(optTask.isEmpty())
                repo.save(task);
        });
    }

}
