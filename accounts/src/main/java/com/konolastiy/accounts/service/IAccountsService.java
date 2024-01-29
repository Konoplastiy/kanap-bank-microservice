package com.konolastiy.accounts.service;

import com.konolastiy.accounts.dto.CustomerDto;
import com.konolastiy.accounts.entity.Customer;

public interface IAccountsService {

    void createAccount(CustomerDto customerDto);

    CustomerDto fetchAccount(String mobileNumber);


    boolean updateAccount(CustomerDto customerDto);

    boolean deleteAccount(String mobileNumber);

}
