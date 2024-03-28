package com.brainvire.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Schema(name = "Account", description = "Holds the information for the Customer Account")
public class AccountsDto {
	@Schema(description = "Account Number of the Customer")
	@NotEmpty(message = "accountNumber can not be Null or Empty")
	@Pattern(regexp = "^$|[0-9]{10}", message = "accountNumber Must be 10 Digits")
	private Long accountNumber;

	@Schema(description = "Account Type of the Customer", example = "Current")
	@NotEmpty(message = "accountType can not be Null or Empty")
	private String accountType;

	@Schema(description = "Branch Address	 of the Customer", example = "Maninagar")
	@NotEmpty(message = "branchAddress can not be Null or Empty")
	private String branchAddress;
}
