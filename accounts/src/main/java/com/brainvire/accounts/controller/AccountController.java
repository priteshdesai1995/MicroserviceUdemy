/*
 * Main the openAPI Documentation: https://www.openapis.org/
 * Add the openAPI in the Spring boot: https://springdoc.org/#getting-started
 * Swagger-ui: http://localhost:8080/swagger-ui/index.html
 */

package com.brainvire.accounts.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.brainvire.accounts.constants.AccountsConstants;
import com.brainvire.accounts.dto.CustomerDto;
import com.brainvire.accounts.dto.ErrorResponseDto;
import com.brainvire.accounts.dto.ResponseDto;
import com.brainvire.accounts.service.IAccountService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "/api", produces = { MediaType.APPLICATION_JSON_VALUE })
@AllArgsConstructor
@Validated
@Tag(name = "Account CRUD APIS Details", description = "This API contains the CRUD API for the Account")
public class AccountController {

//	Logger log = LoggerFactory.getLogger(AccountController.class);

	private IAccountService accountService;

	@GetMapping("/test")
	public String Hello() {
		return "Hello Test";
	}

	@Operation(summary = "This API use for Insert the Customer", description = "This API use for Insert the Customer with the POST Method")
	@ApiResponse(responseCode = "201", description = "Http Status Created")
	@PostMapping("/create")
	public ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody CustomerDto customerDto) {
		accountService.createAccount(customerDto);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new ResponseDto(AccountsConstants.STATUS_201, AccountsConstants.MESSAGE_201));
	}

	@Operation(summary = "This API use for get the Customer", description = "This API use for get the Customer with the Get Method")
	@ApiResponse(responseCode = "200", description = "Http Status FOUND")
	@GetMapping("/fetch")
	public ResponseEntity<CustomerDto> getCutomerWithMobileNumber(
			@RequestParam("mobileNumber") @Pattern(regexp = "^$|[0-9]{10}", message = "accountNumber Must be 10 Digits") String mobileNumber) {
		CustomerDto customer = accountService.getCutomerWithMobileNumber(mobileNumber);
		return ResponseEntity.status(HttpStatus.FOUND).body(customer);
	}

	@Operation(summary = "This API use for update the Customer", description = "This API use for update the Customer with the PUT Method")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "Http Status OK"),
			@ApiResponse(responseCode = "417", description = "Exception Failed"),
			@ApiResponse(responseCode = "500", description = "Http Internal Server Erorr", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))) })
	@PutMapping("/update")
	public ResponseEntity<ResponseDto> updateAccount(@Valid @RequestBody CustomerDto customerDto) {
		boolean isUpdated = accountService.updateAccount(customerDto);
		if (isUpdated) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseDto(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResponseDto(AccountsConstants.STATUS_417, AccountsConstants.MESSAGE_417_UPDATE));
		}
	}

	@Operation(summary = "This API use for delete the Customer", description = "This API use for delete the Customer with the DELETE Method")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "Http Status OK"),
			@ApiResponse(responseCode = "417", description = "Exception Failed"),
			@ApiResponse(responseCode = "500", description = "Http Internal Server Erorr", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))) })
	@DeleteMapping("/delete")
	public ResponseEntity<ResponseDto> deleteAccount(
			@RequestParam("mobileNumber") @Pattern(regexp = "^$|[0-9]{10}", message = "accountNumber Must be 10 Digits") String mobileNumber) {
		boolean isDeleted = accountService.deleteAccount(mobileNumber);
		System.out.println("********** isDeleted is : " + isDeleted);
		if (isDeleted) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseDto(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResponseDto(AccountsConstants.STATUS_417, AccountsConstants.MESSAGE_417_DELETE));
		}
	}

}
