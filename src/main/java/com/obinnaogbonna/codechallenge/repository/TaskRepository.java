package com.obinnaogbonna.codechallenge.repository;

import com.obinnaogbonna.codechallenge.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TaskRepository extends JpaRepository<Task, Long> {

    @Query("Select t FROM Task t WHERE t.name = ?1")
    public Task findByName(String name);
}