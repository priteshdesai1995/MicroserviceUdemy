package com.brainvire.accounts.dto;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(name = "ErrorResponse", description = "Hold the Information about the Error Response")
public class ErrorResponseDto {
	
	@Schema(description = "Holds the API Path", defaultValue = "https://localhost:8080/helloworld")
	private String apiPath;
	
	@Schema(description = "Holds the Error code", defaultValue = "404")
	private HttpStatus errorCode;
	
	@Schema(description = "Holds the Error Message", defaultValue = "server is not reachable")
	private String errorMessage;
	
	@Schema(description = "Holds the Error Time", defaultValue = "24th Nov 2024 12:23:35")
	private LocalDateTime errorTime;
}
