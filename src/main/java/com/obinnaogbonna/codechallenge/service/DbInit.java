package com.obinnaogbonna.codechallenge.service;

import com.obinnaogbonna.codechallenge.entity.Task;
import com.obinnaogbonna.codechallenge.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class DbInit {

    private final PersistenceService persistenceService;

    private final UtilService utilService;

    private final ModelMapper modelMapper;

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
            var optTask = Optional.ofNullable(repo.findByName(task.getName()));
            if(optTask.isEmpty())
                repo.save(task);
        });
    }

}
