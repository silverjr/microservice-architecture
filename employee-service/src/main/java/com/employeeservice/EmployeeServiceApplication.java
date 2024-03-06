package com.employeeservice;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
@EnableFeignClients
@OpenAPIDefinition(
		info = @Info(
				title = "EMPLOYEE SERVICE REST API",
				description = "Spring Boot REST API Documentation",
				version = "v1.0",
				contact = @Contact(
						name = "Silvester",
						email = "silveter.ntunga@gmail.com",
						url = "http://localhost:8081"
				),
				license = @License(
						name = "Apache 2.0",
						url = "https://silver.developers/licence"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "Spring Boot User Management Document",
				url = "http://localhost:8081/user-documentation"
		)
)
public class EmployeeServiceApplication {

	@Bean
	public WebClient webClient(){
		return WebClient.builder().build();
	}

	public static void main(String[] args) {
		SpringApplication.run(EmployeeServiceApplication.class, args);
	}

}
