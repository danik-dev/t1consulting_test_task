package com.danikdev.t1consulting_test_task.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.danikdev.t1consulting_test_task.service.CalculationService;
import com.fasterxml.jackson.core.JsonProcessingException;

@WebMvcTest(CalculationController.class)
@AutoConfigureMockMvc
public class CalculationControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    CalculationService calculationService;

    @Test
    void POST_calculate_validInput() throws Exception {
        String requestJson = """
                    {
                        "input": "aaabbbbc"
                    }
                """;
        String responseJson = "{\"b\":4,\"a\":3,\"c\":1}";

        when(calculationService.calculateEntries("aaabbbbc"))
                .thenReturn(responseJson);

        mockMvc.perform(post("/api/calculate")
                .contentType("application/json")
                .content(requestJson))
                .andExpect(status().isOk())
                .andExpect(content().json(responseJson));
    }

    @Test
    void POST_calculate_badRequest_noJson() throws Exception {
        mockMvc.perform(post("/api/calculate"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("The request must contain json"));
    }

    @Test
    void POST_calculate_badRequest_noJsonKey() throws Exception {
        String requestJson = """
                    {
                        "dummy-key": "aaabbbbc"
                    }
                """;

        mockMvc.perform(post("/api/calculate")
                .contentType("application/json")
                .content(requestJson))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Error parsing json. There is no required \"input\" parameter in sended json."));
    }

    @Test
    void POST_calculate_internalServerError() throws Exception {
        when(calculationService.calculateEntries(anyString()))
            .thenThrow(JsonProcessingException.class);

        mockMvc.perform(post("/api/calculate")
                .contentType("application/json")
                .content("{\"input\": \"aaa\"}"))
                .andExpect(status().isInternalServerError());
    }

}
