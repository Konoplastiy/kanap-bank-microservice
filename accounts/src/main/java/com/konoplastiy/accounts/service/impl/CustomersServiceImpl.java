package com.konoplastiy.accounts.service.impl;

import com.konoplastiy.accounts.dto.AccountsDto;
import com.konoplastiy.accounts.dto.CardsDto;
import com.konoplastiy.accounts.dto.CustomerDetailsDto;
import com.konoplastiy.accounts.dto.LoansDto;
import com.konoplastiy.accounts.entity.Accounts;
import com.konoplastiy.accounts.entity.Customer;
import com.konoplastiy.accounts.exception.ResourceNotFoundException;
import com.konoplastiy.accounts.mapper.AccountsMapper;
import com.konoplastiy.accounts.mapper.CustomerMapper;
import com.konoplastiy.accounts.repository.AccountsRepository;
import com.konoplastiy.accounts.repository.CustomerRepository;
import com.konoplastiy.accounts.service.ICustomersService;
import com.konoplastiy.accounts.service.client.CardsFeignClient;
import com.konoplastiy.accounts.service.client.LoansFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomersServiceImpl implements ICustomersService {

    private final AccountsRepository accountsRepository;
    private final CustomerRepository customerRepository;
    private final CardsFeignClient cardsFeignClient;
    private final LoansFeignClient loansFeignClient;

    /**
     * @param mobileNumber - Input Mobile Number
     * @return Customer Details based on a given mobileNumber
     */
    @Override
    public CustomerDetailsDto fetchCustomerDetails(String mobileNumber, String correlationId) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );
        Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                () -> new ResourceNotFoundException("Account", "customerId", customer.getCustomerId().toString())
        );

        CustomerDetailsDto customerDetailsDto = CustomerMapper.mapToCustomerDetailsDto(customer, new CustomerDetailsDto());
        customerDetailsDto.setAccountsDto(AccountsMapper.mapToAccountsDto(accounts, new AccountsDto()));

        ResponseEntity<LoansDto> loansDtoResponseEntity = loansFeignClient.fetchLoanDetails(correlationId, mobileNumber);
        if(null != loansDtoResponseEntity) {
            customerDetailsDto.setLoansDto(loansDtoResponseEntity.getBody());
        }

        ResponseEntity<CardsDto> cardsDtoResponseEntity = cardsFeignClient.fetchCardDetails(correlationId, mobileNumber);
        if(null != cardsDtoResponseEntity) {
            customerDetailsDto.setCardsDto(cardsDtoResponseEntity.getBody());
        }

        return customerDetailsDto;

    }
}
