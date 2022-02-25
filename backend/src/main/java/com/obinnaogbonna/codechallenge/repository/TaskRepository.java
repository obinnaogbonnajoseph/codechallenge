package com.obinnaogbonna.codechallenge.repository;

import com.obinnaogbonna.codechallenge.entity.Task;
import com.obinnaogbonna.codechallenge.util.CodeLanguage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TaskRepository extends JpaRepository<Task, Long> {

    @Query("Select t FROM Task t WHERE t.name = ?1 and t.type = ?2")
    public Task findByNameAndType(String name, CodeLanguage type);
}
