package com.danikdev.t1consulting_test_task.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
    info = @Info(
        contact = @Contact(
            name = "Daniil",
            email = "top.man.danila@gmail.com",
            url = "https://www.linkedin.com/in/daniil-harbachou-b4825a234/"
        ), 
        description = "OpenApi documentation for t1-consulting test task", 
        title = "OpenApi specification", 
        version = "1.0"      
    )
)
public class OpenApiConfig {
    
}
