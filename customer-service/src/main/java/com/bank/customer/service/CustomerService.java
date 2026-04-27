package com.bank.customer.service;

import com.bank.customer.dto.CustomerCreateRequest;
import com.bank.customer.dto.CustomerResponse;

public interface CustomerService {

    CustomerResponse create(CustomerCreateRequest request);

    CustomerResponse getById(Long id);
}
