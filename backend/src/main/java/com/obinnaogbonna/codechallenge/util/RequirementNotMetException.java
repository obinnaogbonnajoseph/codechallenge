package com.obinnaogbonna.codechallenge.util;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class RequirementNotMetException extends Exception {

    public RequirementNotMetException() {
        super();
    }

    public RequirementNotMetException(String message) {
        super(message);
    }

}
