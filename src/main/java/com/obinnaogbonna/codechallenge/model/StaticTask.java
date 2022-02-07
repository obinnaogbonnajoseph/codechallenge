package com.obinnaogbonna.codechallenge.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConstructorBinding
@RequiredArgsConstructor
public class StaticTask {

    @Getter
    private final String name;

    @Getter
    private final String description;

    @Getter
    private final String starterCode;

    @Getter
    private final String answer;

}
