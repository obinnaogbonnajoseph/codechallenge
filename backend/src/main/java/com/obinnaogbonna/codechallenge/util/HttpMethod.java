package com.obinnaogbonna.codechallenge.util;

import lombok.Getter;

public enum HttpMethod {
    POST("POST"), GET("GET"), PUT("PUT");

    @Getter
    private String name;

    HttpMethod(String name) {
        this.name = name;
    }
}
