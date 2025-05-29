package com.skoziol.accounts.service.impl;

import com.skoziol.accounts.dto.AccountsDto;
import com.skoziol.accounts.dto.CustomerDto;
import com.skoziol.accounts.entity.Accounts;
import com.skoziol.accounts.entity.Customer;
import com.skoziol.accounts.exception.CustomerAlreadyExistsException;
import com.skoziol.accounts.exception.ResourceNotFoundException;
import com.skoziol.accounts.mapper.AccountsMapper;
import com.skoziol.accounts.mapper.CustomerMapper;
import com.skoziol.accounts.repository.AccountsRepository;
import com.skoziol.accounts.repository.CustomerRepository;
import com.skoziol.accounts.service.IAccountsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class AccountsServiceImpl implements IAccountsService {

    public static final String SAVINGS = "Savings";
    public static final String ADDRESS = "Kurkowa Street, Wroclaw 50-210";
    private final AccountsRepository accountsRepository;
    private final CustomerRepository customerRepository;

    /**
     * @param customerDto - CustomerDto Object
     */
    @Override
    public void createAccount(CustomerDto customerDto) {
        Customer customer = CustomerMapper.mapToCustomer(new Customer(), customerDto);
        customerRepository.findByMobileNumber(customerDto.mobileNumber())
                .ifPresent((value) -> {
                    throw new CustomerAlreadyExistsException("Customer already registered with given mobileNumber: "
                            + customerDto.mobileNumber());
                });
        Customer savedCustomer = customerRepository.save(customer);

        Accounts newAccount = createNewAccount(savedCustomer);
        accountsRepository.save(newAccount);
    }

    /**
     * @param mobileNumber - Input Mobile Number
     * @return Accounts details based on a given number
     */
    @Override
    public CustomerDto fetchAccount(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );

        Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                () -> new ResourceNotFoundException("Account", "customerId", customer.getCustomerId().toString())
        );
        AccountsDto accountsDto = AccountsMapper.mapToAccountsDto(accounts);
        return CustomerMapper.mapToCustomerDto(customer, accountsDto);
    }

    /**
     * @param customerDto - CustomerDto Object
     * @return boolean indicating if the update of Account details is successful or not
     */
    @Override
    public boolean updateAccount(CustomerDto customerDto) {
       boolean isUpdated = false;
        AccountsDto accountsDto = customerDto.accountsDto();
        if(accountsDto != null) {

            Accounts accounts = accountsRepository.findById(accountsDto.accountNumber()).orElseThrow(
                    () -> new ResourceNotFoundException("Accounts", "AccountNumber", accountsDto.accountNumber().toString())
            );
            AccountsMapper.mapToAccounts(accounts, accountsDto);
            accounts = accountsRepository.save(accounts);

            Long customerId = accounts.getCustomerId();
            Customer customer = customerRepository.findById(customerId).orElseThrow(
                    () -> new ResourceNotFoundException("Customer", "CustomerID", customerId.toString())
            );
            CustomerMapper.mapToCustomer(customer, customerDto);
            customerRepository.save(customer);
            isUpdated = true;
        }
        return isUpdated;
    }

    /**
     * @param mobileNumber - Input Mobile Number
     * @return boolean indicating if the delete of Account is successful or not
     */
    @Override
    public boolean deleteAccount(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );
        accountsRepository.deleteByCustomerId(customer.getCustomerId());
        customerRepository.deleteById(customer.getCustomerId());

        return true;
    }

    /**
     * @param customer - Customer Object
     * @return the new account details
     */
    private Accounts createNewAccount(Customer customer) {
        Accounts newAccount = new Accounts();
        newAccount.setCustomerId(customer.getCustomerId());

        long randomAccNumber = 1000000000L + new Random().nextInt(90000000);
        newAccount.setAccountNumber(randomAccNumber);

        newAccount.setAccountType(SAVINGS);
        newAccount.setBranchAddress(ADDRESS);
        return newAccount;
    }
}
