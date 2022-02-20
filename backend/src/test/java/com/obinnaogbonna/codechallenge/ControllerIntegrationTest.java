package com.obinnaogbonna.codechallenge;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.obinnaogbonna.codechallenge.model.RequestDto;
import com.obinnaogbonna.codechallenge.util.CodeLanguage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;


import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest()
@AutoConfigureMockMvc
public class ControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void givenJavaRequest_whenCorrectCode_thenStatus200() {
        RequestDto dto = new RequestDto("Jude James", "CamelCase", CodeLanguage.JAVA, Constants.getJavaStarterCode());
        try {
            mockMvc.perform(
                    post("/task/submit")
                            .content(testInsertObject(dto))
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                    .andDo(print());
        } catch (Exception e) {
            fail("Should not throw exception");
        }
    }

    @Test
    public void givenNodejsRequest_whenCorrectCode_thenStatus200() {
        RequestDto dto = new RequestDto("Chekwube James", "CamelCase", CodeLanguage.NodeJS, Constants.getJavaScriptStarterCode());
        try {
            mockMvc.perform(
                            post("/task/submit")
                                    .content(testInsertObject(dto))
                                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                    .andDo(print());
        } catch (Exception e) {
            fail("Should not throw exception");
        }
    }

    @Test
    public void getTasks_whenCorrectCode_thenStatus200() {
        try {
            mockMvc.perform(
                    get("/task")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$.[0].name", is("CamelCase")))
                    .andDo(print());
        } catch (Exception e) {
            fail("Should not throw exception");
        }
    }

    private String testInsertObject(RequestDto obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = objectMapper.writer().withDefaultPrettyPrinter();
        return ow.writeValueAsString(obj);
    }

}
