package com.brainvire.accounts.mapper;

import com.brainvire.accounts.dto.CustomerDto;
import com.brainvire.accounts.entity.Customer;

public class CustomerMapper {
	public static CustomerDto mapToCustomerDto(CustomerDto customerDto, Customer customer) {
		customerDto.setEmail(customer.getEmail());
		customerDto.setMobileNumber(customer.getMobileNumber());
		customerDto.setName(customer.getName());
		return customerDto;
	}

	public static Customer mapToCustomers(CustomerDto customerDto, Customer customer) {
		customer.setEmail(customerDto.getEmail());
		customer.setMobileNumber(customerDto.getMobileNumber());
		customer.setName(customerDto.getName());
		return customer;
	}
}
