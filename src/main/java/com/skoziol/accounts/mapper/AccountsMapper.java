package com.skoziol.accounts.mapper;

import com.skoziol.accounts.dto.AccountsDto;
import com.skoziol.accounts.entity.Accounts;

public class AccountsMapper {

    public static AccountsDto mapToAccountsDto(Accounts accounts) {
        return new AccountsDto(
                accounts.getAccountNumber(),
                accounts.getAccountType(),
                accounts.getBranchAddress()
        );
    }

    public static Accounts mapToAccounts(Accounts accounts, AccountsDto accountsDto) {
        accounts.setAccountNumber(accountsDto.accountNumber());
        accounts.setAccountType(accountsDto.accountType());
        accounts.setBranchAddress(accountsDto.branchAddress());
        return accounts;
    }
}
