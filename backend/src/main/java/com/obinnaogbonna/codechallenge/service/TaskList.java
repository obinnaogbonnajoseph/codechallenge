package com.obinnaogbonna.codechallenge.service;

import com.obinnaogbonna.codechallenge.model.StaticTask;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;


@Configuration
@ConfigurationProperties("static")
public class TaskList {

    private final ArrayList<StaticTask> tasks;


    public TaskList(ArrayList<StaticTask> tasks) {
        this.tasks = tasks;
    }

    public List<StaticTask> getTasks(){
        return tasks;
    }

}
