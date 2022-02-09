package com.obinnaogbonna.codechallenge.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskRequest {

    @NotBlank
    private String name;

    @NotBlank
    private String code;

    @NotBlank
    private String description;
}
