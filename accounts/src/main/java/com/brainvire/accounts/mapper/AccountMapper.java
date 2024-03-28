package com.brainvire.accounts.mapper;

import com.brainvire.accounts.dto.AccountsDto;
import com.brainvire.accounts.entity.Accounts;

public class AccountMapper {
	public static AccountsDto mapToAccountsDto(AccountsDto accountsDto, Accounts accounts) {
		accountsDto.setAccountNumber(accounts.getAccountNumber());
		accountsDto.setAccountType(accounts.getAccountType());
		accountsDto.setBranchAddress(accounts.getBranchAddress());
		return accountsDto;
	}

	public static Accounts mapToAccounts(AccountsDto accountsDto, Accounts accounts) {
		accounts.setAccountNumber(accountsDto.getAccountNumber());
		accounts.setAccountType(accountsDto.getAccountType());
		accounts.setBranchAddress(accountsDto.getBranchAddress());
		return accounts;
	}
}
