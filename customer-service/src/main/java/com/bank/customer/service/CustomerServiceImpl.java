package com.bank.customer.service;

import com.bank.customer.dto.CustomerCreateRequest;
import com.bank.customer.dto.CustomerResponse;
import com.bank.customer.entity.Customer;
import com.bank.customer.exception.CustomerNotFoundException;
import com.bank.customer.exception.DuplicateCustomerException;
import com.bank.customer.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository repository;

    @Override
    public CustomerResponse create(CustomerCreateRequest request) {
        if (repository.existsByMobile(request.mobile())) {
            throw new DuplicateCustomerException(
                    "Mobile already registered");
        }

        if (repository.existsByPan(request.pan())) {
            throw new DuplicateCustomerException(
                    "PAN already registered");
        }

        Customer customer = Customer.builder()
                .fullName(request.fullName())
                .mobile(request.mobile())
                .pan(request.pan())
                .email(request.email())
                .address(request.address())
                .build();

        Customer saved = repository.save(customer);

        return map(saved);
    }

        private CustomerResponse map(Customer c) {
        return new CustomerResponse(
                c.getId(),
                c.getFullName(),
                c.getMobile(),
                c.getEmail(),
                c.getPan(),
                c.getAddress()
        );
    }

    @Override
    public CustomerResponse getById(Long id) {
        Customer customer = repository.findById(id)
                .orElseThrow(() ->
                        new CustomerNotFoundException(
                                "Customer not found"));

        return map(customer);
    }
}
