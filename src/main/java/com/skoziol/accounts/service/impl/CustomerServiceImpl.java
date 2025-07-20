package com.skoziol.accounts.service.impl;

import com.skoziol.accounts.dto.CardsDto;
import com.skoziol.accounts.dto.CustomerDetailsDto;
import com.skoziol.accounts.dto.LoansDto;
import com.skoziol.accounts.entity.Accounts;
import com.skoziol.accounts.entity.Customer;
import com.skoziol.accounts.exception.ResourceNotFoundException;
import com.skoziol.accounts.mapper.CustomerMapper;
import com.skoziol.accounts.repository.AccountsRepository;
import com.skoziol.accounts.repository.CustomerRepository;
import com.skoziol.accounts.service.ICustomerService;
import com.skoziol.accounts.service.client.CardsFeignClient;
import com.skoziol.accounts.service.client.LoansFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements ICustomerService {

    private final AccountsRepository accountsRepository;
    private final CustomerRepository customerRepository;
    private final CardsFeignClient cardsFeignClient;
    private final LoansFeignClient loansFeignClient;


    /**
     * @param mobileNumber - Input Mobile Number
     * @return Customer Details based on a given Mobile Number
     */
    @Override
    public CustomerDetailsDto fetchCustomerDetails(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );

        Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                () -> new ResourceNotFoundException("Account", "customerId", customer.getCustomerId().toString())
        );

        LoansDto loansDto = loansFeignClient.fetchLoanDetails(mobileNumber).getBody();

        CardsDto cardsDto = cardsFeignClient.fetchCardDetails(mobileNumber).getBody();

        return CustomerMapper.mapToCustomerDetailsDto(customer, accounts, loansDto, cardsDto);
    }
}
