package com.obinnaogbonna.codechallenge.model;

import lombok.Getter;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConstructorBinding
public class StaticTask {

    @Getter
    private final String name;

    @Getter
    private final String description;

    @Getter
    private final String starterCode;

    @Getter
    private final String answer;

    StaticTask(String description, String starterCode, String answer, String name) {
        this.description = description;
        this.starterCode = starterCode;
        this.answer = answer;
        this.name = name;
    }

}
