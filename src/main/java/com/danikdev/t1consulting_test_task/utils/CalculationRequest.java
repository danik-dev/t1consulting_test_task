package com.danikdev.t1consulting_test_task.utils;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema
public class CalculationRequest {
    
    @Schema(example = "aaabbbbc")
    @NotNull
    private String input;
}
