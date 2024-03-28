package com.brainvire.accounts.service.impl;

import java.util.Optional;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.brainvire.accounts.constants.AccountsConstants;
import com.brainvire.accounts.dto.AccountsDto;
import com.brainvire.accounts.dto.CustomerDto;
import com.brainvire.accounts.entity.Accounts;
import com.brainvire.accounts.entity.Customer;
import com.brainvire.accounts.exception.CustomerAlreadyExsistException;
import com.brainvire.accounts.exception.ResourceNotFoundException;
import com.brainvire.accounts.mapper.AccountMapper;
import com.brainvire.accounts.mapper.CustomerMapper;
import com.brainvire.accounts.repository.AccountRepository;
import com.brainvire.accounts.repository.CustomerRepository;
import com.brainvire.accounts.service.IAccountService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements IAccountService {

	private CustomerRepository customerRepository;
	private AccountRepository accountRepository;

	/**
	 *
	 */
	@Override
	public void createAccount(CustomerDto customerDto) {
		// TODO Auto-generated method stub
		Customer customer = CustomerMapper.mapToCustomers(customerDto, new Customer());

		// check the validation with one email only one Customer are allowed
		Optional<Customer> customerWithEmail = customerRepository.findByMobileNumber(customerDto.getMobileNumber());
		if (customerWithEmail.isPresent()) {
			throw new CustomerAlreadyExsistException(
					"Customer Already Exsist with the give Email Address: " + customerDto.getEmail());
		}

		// Add the Audit Field
//		customer.setCreatedAt(LocalDateTime.now());
//		customer.setUpdatedAt(LocalDateTime.now());
//		customer.setCreatedBy(customer.getName());
//		customer.setUpdatedBy(customer.getName());

		Customer savedCustomer = customerRepository.save(customer);
		Accounts accounts = CreateNewAccount(savedCustomer);
//		accounts.setCreatedAt(LocalDateTime.now());
//		accounts.setUpdatedAt(LocalDateTime.now());
//		accounts.setCreatedBy(customer.getName());
//		accounts.setUpdatedBy(customer.getName());
		accountRepository.save(accounts);
	}

	public Accounts CreateNewAccount(Customer customer) {
		Accounts accounts = new Accounts();
		accounts.setCustomerId(customer.getCustomerId());

		long accountNumber = 1000000L + new Random().nextInt(9000000);
		accounts.setAccountNumber(accountNumber);
		accounts.setAccountType(AccountsConstants.SAVINGS);
		accounts.setBranchAddress(AccountsConstants.ADDRESS);
		return accounts;
	}

	@Override
	public CustomerDto getCutomerWithMobileNumber(String mobileNumber) {
		Customer customer = customerRepository.findByMobileNumber(mobileNumber)
				.orElseThrow(() -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber));

		Accounts account = accountRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
				() -> new ResourceNotFoundException("Account", "customerId", customer.getCustomerId().toString()));

		CustomerDto customerDto = CustomerMapper.mapToCustomerDto(new CustomerDto(), customer);
		AccountsDto accountsDto = AccountMapper.mapToAccountsDto(new AccountsDto(), account);
		customerDto.setAccountsDto(accountsDto);
		return customerDto;
	}

	@Override
	public boolean updateAccount(CustomerDto customerDto) {
		boolean isUpdated = false;
		AccountsDto accountsDto = customerDto.getAccountsDto();
		if (accountsDto != null) {
			Accounts account = accountRepository.findById(accountsDto.getAccountNumber())
					.orElseThrow(() -> new ResourceNotFoundException("Account", "AccountNumber",
							accountsDto.getAccountNumber().toString()));

			AccountMapper.mapToAccounts(accountsDto, account);
			accountRepository.save(account);

			Long customerId = account.getCustomerId();
			Customer customer = customerRepository.findById(customerId)
					.orElseThrow(() -> new ResourceNotFoundException("Customer", "CustomerId", customerId.toString()));
			CustomerMapper.mapToCustomers(customerDto, customer);
			customerRepository.save(customer);

			isUpdated = true;
		}

		return isUpdated;
	}

	@Override
	public boolean deleteAccount(String mobileNumber) {
		Customer customer = customerRepository.findByMobileNumber(mobileNumber)
				.orElseThrow(() -> new ResourceNotFoundException("Customer", "MobileNumber", mobileNumber));

		accountRepository.deleteByCustomerId(customer.getCustomerId());
		customerRepository.deleteById(customer.getCustomerId());
		return true;
	}

}
