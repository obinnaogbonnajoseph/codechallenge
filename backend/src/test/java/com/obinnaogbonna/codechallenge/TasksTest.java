package com.obinnaogbonna.codechallenge;

import com.obinnaogbonna.codechallenge.service.TaskList;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;


@SpringBootTest()
@ActiveProfiles("test")
public class TasksTest {

    @Autowired
    private TaskList taskList;

    @Test
    void tasksAreLoaded() {
        assertEquals(taskList.getTasks().size(), 10);
    }
}
