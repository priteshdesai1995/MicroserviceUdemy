package com.brainvire.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(
		name = "Customer",
		description = "Holds the Information About the Customer"
		)
public class CustomerDto {

	@Schema(description = "Customer Name", example = "Jhon")
	@NotEmpty(message = "Name can not be Null or Empty")
	@Size(min = 5, max = 30, message = "Name should be 5 to 30 characters long")
	private String name;

	@Schema(description = "Customer Email", example = "jhon@gmail.com")
	@NotEmpty(message = "Email can not be Null or Empty")
	@Email
	private String email;
	
	@Schema(name = "Customer Mobile Number", example = "1234567890")
	@NotEmpty(message = "mobileNumber can not be Null or Empty")
	@Pattern(regexp = "^$|[0-9]{10}",message = "Mobile Number Must be 10 Digits")
	private String mobileNumber;

	private AccountsDto accountsDto;
}
