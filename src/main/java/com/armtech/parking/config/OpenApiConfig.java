package com.armtech.parking.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(title = "Cloud Parking API", version = "1.0", description = "Parking API"))
public class OpenApiConfig {
}
