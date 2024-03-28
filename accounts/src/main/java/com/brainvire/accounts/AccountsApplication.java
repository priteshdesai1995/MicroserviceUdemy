package com.brainvire.accounts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@OpenAPIDefinition(
		info = @Info(title = "Accounts Microservice REST API Documentation", 
					 description = "Bank Account MicroSErvices REST API",
					 version = "v1",
					 contact = @Contact(
						name = "Pritesh Desai",
						email = "priteshdesai1811@gmail.com",
						url="www.google.com"),
						license = @License(name = "Apache 2.0",
							url = "www.google.com")
						),
						externalDocs = @ExternalDocumentation(
							description = "Bank Account	Microservice Document",
							url = "www.google.com"
						)
				)
public class AccountsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountsApplication.class, args);
	}

}
