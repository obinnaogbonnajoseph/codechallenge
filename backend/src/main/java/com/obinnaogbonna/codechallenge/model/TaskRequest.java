package com.obinnaogbonna.codechallenge.model;

import com.obinnaogbonna.codechallenge.util.CodeLanguage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskRequest {

    @NotBlank
    private String name;

    @NotNull
    private CodeLanguage type;

    @NotBlank
    private String code;

    @NotBlank
    private String description;
}
