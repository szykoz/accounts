package com.skoziol.accounts.service;

import com.skoziol.accounts.dto.CustomerDetailsDto;

public interface ICustomerService {

    /**
     *
     * @param mobileNumber - Input Mobile Number
     * @return Customer Details based on a given Mobile Number
     */
    CustomerDetailsDto fetchCustomerDetails(String mobileNumber, String correlationId);
}
