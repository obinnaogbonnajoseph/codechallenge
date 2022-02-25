package com.obinnaogbonna.codechallenge.model;

import com.obinnaogbonna.codechallenge.util.CodeLanguage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestDto {

    private String userName;

    private String taskName;

    private CodeLanguage type;

    private String code;
}
