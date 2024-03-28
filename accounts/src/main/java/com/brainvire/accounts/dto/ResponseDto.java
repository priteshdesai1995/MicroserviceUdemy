package com.brainvire.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(name = "Response", description = "Used for the Response DTO")
public class ResponseDto {
	@Schema(description = "Holds the information about the status code")
	private String statusCode;
	@Schema(description = "Holds the information about the status Message")
	private String statusMsg;
}
