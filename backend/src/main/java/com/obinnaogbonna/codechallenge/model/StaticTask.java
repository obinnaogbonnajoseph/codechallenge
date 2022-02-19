package com.obinnaogbonna.codechallenge.model;

import com.obinnaogbonna.codechallenge.util.CodeLanguage;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConstructorBinding
@RequiredArgsConstructor
public class StaticTask {

    @Getter
    private final String name;

    @Getter
    private final CodeLanguage type;

    @Getter
    private final String description;

    @Getter
    private final String starterCode;

    @Getter
    private final String answer;

}
