package com.obinnaogbonna.codechallenge;

import com.obinnaogbonna.codechallenge.model.TaskRequest;
import com.obinnaogbonna.codechallenge.service.TaskService;
import com.obinnaogbonna.codechallenge.util.CodeLanguage;
import com.obinnaogbonna.codechallenge.util.RequirementNotMetException;
import com.obinnaogbonna.codechallenge.util.ResourceNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest
public class ServiceImplTest {

    @Autowired
    private TaskService taskService;

    @Test
    public void should_getJavaScore_success() {
        var taskRequest = new TaskRequest
                ("CamelCase", CodeLanguage.JAVA, Constants.getJavaStarterCode(),
                        "Convert given input to CamelCase. Code is written in Java. Implement solution on the mySolution method.");

        try {
            int score = taskService.getScore(taskRequest);
            assertThat(score, greaterThanOrEqualTo(20));
        } catch (IOException | RequirementNotMetException | ResourceNotFoundException | URISyntaxException | InterruptedException e) {
            fail("Should not throw exception");
        }
    }

    @Test
    public void should_getJavaScriptScore_success() {
        var taskRequest = new TaskRequest
                ("CamelCase", CodeLanguage.NodeJS, Constants.getJavaScriptStarterCode(),
                        "Convert given input to CamelCase. Code is written in JavaScript. Implement solution on the mySolution method.");

        try {
            int score = taskService.getScore(taskRequest);
            assertThat(score, greaterThanOrEqualTo(20));
        } catch (IOException | RequirementNotMetException | ResourceNotFoundException | URISyntaxException | InterruptedException e) {
            fail("Should not throw exception");
        }
    }

    @Test
    public void should_findJavaTask_success() {
        String name = "CamelCase";
        CodeLanguage type = CodeLanguage.JAVA;
        try {
            var task = taskService.findByNameAndType(name, type);
            assertEquals(task.getName(), name);
            assertEquals(task.getType(), type);
        } catch (ResourceNotFoundException e) {
            fail("Should not throw exception");
        }
    }

    @Test
    public void should_findJSTask_success() {
        String name = "CamelCase";
        CodeLanguage type = CodeLanguage.NodeJS;
        try {
            var task = taskService.findByNameAndType(name, type);
            assertEquals(task.getName(), name);
            assertEquals(task.getType(), type);
        } catch (ResourceNotFoundException e) {
            fail("Should not throw exception");
        }
    }
}
