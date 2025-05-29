package com.skoziol.accounts.mapper;

import com.skoziol.accounts.dto.AccountsDto;
import com.skoziol.accounts.dto.CustomerDto;
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
}
