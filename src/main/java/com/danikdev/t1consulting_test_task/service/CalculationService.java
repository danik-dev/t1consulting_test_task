package com.danikdev.t1consulting_test_task.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Comparator;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Service
public class CalculationService {

    public String calculateEntries(String input) throws JsonProcessingException {
        if (input == null) {
            throw new NumberFormatException("Cannot parse null string");
        }
        char[] inputChar = input.toCharArray();
        Map<Character, Integer> correspondenceMap = new HashMap<>();

        for (Character c : inputChar) {
            if (correspondenceMap.get(c) == null) {
                correspondenceMap.put(c, 1);
            } else {
                Integer counter = correspondenceMap.get(c);
                correspondenceMap.put(c, ++counter);
            }
        }
        
        ObjectNode jsonObject = JsonNodeFactory.instance.objectNode();

        correspondenceMap.entrySet().stream()
                .sorted(Comparator.comparing(Map.Entry<Character, Integer>::getValue).reversed())
                .forEach(entry -> jsonObject.put(entry.getKey().toString(), entry.getValue()));

        return new ObjectMapper().writeValueAsString(jsonObject);
    }
}
