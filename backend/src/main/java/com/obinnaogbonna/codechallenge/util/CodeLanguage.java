package com.obinnaogbonna.codechallenge.util;

import lombok.Getter;

public enum CodeLanguage {

    JAVA("java"), NodeJS("nodejs");

    @Getter
    private String name;

    CodeLanguage(String name) {
        this.name = name;
    }
}
