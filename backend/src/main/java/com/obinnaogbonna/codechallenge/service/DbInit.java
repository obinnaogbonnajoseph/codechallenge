package com.obinnaogbonna.codechallenge.service;

import com.obinnaogbonna.codechallenge.entity.Task;
import com.obinnaogbonna.codechallenge.repository.TaskRepository;
import com.obinnaogbonna.codechallenge.util.CodeLanguage;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;

@Component
public class DbInit {

    private final PersistenceService persistenceService;

    private final UtilService utilService;

    private final ModelMapper modelMapper;

    @Autowired
    public DbInit(PersistenceService persistenceService, UtilService utilService, ModelMapper modelMapper) {
        this.persistenceService = persistenceService;
        this.utilService = utilService;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    private void postConstruct() {
        List<Task> tasks = this.utilService
                .getTaskList()
                .getTasks()
                .stream()
                .map(task -> {
                    Task newTask = new Task();
                    modelMapper.map(task, newTask);
                    return newTask;
                }).toList();
        TaskRepository repo = this.persistenceService.getTaskRepository();
        tasks.forEach(task -> {
            var optTask = Optional.ofNullable(repo.findByNameAndType(task.getName(), task.getType()));
            if(optTask.isEmpty())
                repo.save(task);
        });
    }

}
