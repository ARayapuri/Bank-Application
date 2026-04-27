package com.bank.customer.controller;

import com.bank.customer.dto.CustomerCreateRequest;
import com.bank.customer.dto.CustomerResponse;
import com.bank.customer.service.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerControllerTest {

    @Mock
    private CustomerService service;

    @InjectMocks
    private CustomerController controller;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void shouldReturn201Created() {

        CustomerResponse response =
                new CustomerResponse(
                        1L,
                        "Ashok",
                        "9876543210",
                        "a@gmail.com",
                        "ABCDE1234F",
                        "Hyd"
                );

        CustomerCreateRequest request =
                new CustomerCreateRequest(
                        "Ashok",
                        "9876543210",
                        "a@gmail.com",
                        "ABCDE1234F",
                        "Hyd"
                );

        when(service.create(any(CustomerCreateRequest.class)))
                .thenReturn(response);

        ResponseEntity<?> result = controller.create(request);

        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertEquals("Customer created",
                ((com.bank.customer.dto.SuccessResult) result.getBody()).message());
    }
}