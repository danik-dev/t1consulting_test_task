package com.danikdev.t1consulting_test_task.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.danikdev.t1consulting_test_task.service.CalculationService;
import com.danikdev.t1consulting_test_task.utils.CalculationRequest;
import com.fasterxml.jackson.core.JsonProcessingException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CalculationController {

    private final CalculationService calculationService;

    @Operation(
        description = "Post endpint for character entries calculation",
        summary = "Custom summury",
        responses = {
            @ApiResponse(
                description = "Success",
                responseCode = "200",
                content = @Content(
                    mediaType = "application/json",
                    examples = @ExampleObject(
                        value = "{\"b\":4,\"a\":3,\"c\":1}"
                    )
                )
            ),
            @ApiResponse(
                description = "Bad request",
                responseCode = "400",
                content = @Content(
                    examples = @ExampleObject(
                        value = "Error parsing json. There is no required \"input\" parameter in sended json."
                    )
                )
            ),
            @ApiResponse(
                description = "Internal server error",
                responseCode = "500",
                content = @Content(
                    examples = @ExampleObject(
                        value = "Calculations on server are failed."
                    )
                )
            )
        }
    )
    @PostMapping("/calculate")
    public ResponseEntity<?> calculate(@Valid @RequestBody CalculationRequest request, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().body("Error parsing json. There is no required \"input\" parameter in sended json.");
        }
        
        try {
            return ResponseEntity.ok().body(calculationService.calculateEntries(request.getInput()));
        } catch (JsonProcessingException e) {
            return ResponseEntity.internalServerError().body("Calculations on server are failed");
        }
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleValidationException(HttpMessageNotReadableException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("The request must contain json");
    }
}
