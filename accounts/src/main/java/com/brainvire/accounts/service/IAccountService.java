package com.brainvire.accounts.service;

import com.brainvire.accounts.dto.CustomerDto;

public interface IAccountService {

	/**
	 * @param accountsDto: accountsDto object
	 */
	void createAccount(CustomerDto accountsDto);

	CustomerDto getCutomerWithMobileNumber(String mobileNumber);

	boolean updateAccount(CustomerDto customerDto);
	
	boolean deleteAccount(String mobileNumber);

}
