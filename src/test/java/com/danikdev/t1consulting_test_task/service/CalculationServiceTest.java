package com.danikdev.t1consulting_test_task.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fasterxml.jackson.core.JsonProcessingException;

@ExtendWith(MockitoExtension.class)
public class CalculationServiceTest {

    @Spy
    private CalculationService calculationService;

    @Test
    void CalculationService_CalculateEntries_InputNull() {
        assertThrows(NumberFormatException.class,
            () -> calculationService.calculateEntries(null));
    }

    @Test
    void CalculationService_CalculateEntries_InputEmpty() throws JsonProcessingException {
        assertEquals("{}", calculationService.calculateEntries(""));
    }

    @Test
    void CalculationService_CalculateEntries_InputNormal() throws JsonProcessingException {
        assertEquals("{\"b\":4,\"a\":3,\"c\":1}", calculationService.calculateEntries("aaabbbbc"));
    }
}