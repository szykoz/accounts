package com.skoziol.accounts.mapper;

import com.skoziol.accounts.dto.*;
import com.skoziol.accounts.entity.Accounts;
import com.skoziol.accounts.entity.Customer;

public class CustomerMapper {

    public static CustomerDto mapToCustomerDto(Customer customer, AccountsDto accountsDto) {
        return new CustomerDto(
                customer.getName(),
                customer.getEmail(),
                customer.getMobileNumber(),
                accountsDto
        );
    }

    public static Customer mapToCustomer(Customer customer, CustomerDto customerDto) {
        customer.setName(customerDto.name());
        customer.setEmail(customerDto.email());
        customer.setMobileNumber(customerDto.mobileNumber());
        return customer;
    }

    public static CustomerDetailsDto mapToCustomerDetailsDto(Customer customer, Accounts accounts, LoansDto loansDto, CardsDto cardsDto) {
        return new CustomerDetailsDto(
                customer.getName(),
                customer.getEmail(),
                customer.getMobileNumber(),
                AccountsMapper.mapToAccountsDto(accounts),
                loansDto,
                cardsDto
        );
    }
}
