package com.asimov.teachers;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition
@SpringBootApplication
public class TeachersApplication {

	public static void main(String[] args) {
		SpringApplication.run(TeachersApplication.class, args);
	}

}
