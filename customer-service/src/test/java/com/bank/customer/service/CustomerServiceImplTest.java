package com.bank.customer.service;

import com.bank.customer.dto.CustomerCreateRequest;
import com.bank.customer.dto.CustomerResponse;
import com.bank.customer.entity.Customer;
import com.bank.customer.exception.CustomerNotFoundException;
import com.bank.customer.exception.DuplicateCustomerException;
import com.bank.customer.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertThrows;


@ExtendWith(MockitoExtension.class)
class CustomerServiceImplTest {

    @Mock
    private CustomerRepository repository;

    @InjectMocks
    private CustomerServiceImpl service;

    @Test
    void shouldCreateCustomerSuccessfully() {

        CustomerCreateRequest request =
                new CustomerCreateRequest(
                        "Ashok",
                        "9876543210",
                        "ABCDE1234F",
                        "a@gmail.com",
                        "Hyd");

        when(repository.existsByMobile("9876543210"))
                .thenReturn(false);

        when(repository.existsByPan("ABCDE1234F"))
                .thenReturn(false);

        Customer saved = Customer.builder()
                .id(1L)
                .fullName("Ashok")
                .mobile("9876543210")
                .email("a@gmail.com")
                .pan("ABCDE1234F")
                .address("Hyd")
                .build();

        when(repository.save(any(Customer.class)))
                .thenReturn(saved);

        CustomerResponse response =
                service.create(request);//

        assertEquals(1L, response.id());
        assertEquals("Ashok", response.fullName());
    }

    @Test
    void shouldThrowDuplicateWhenMobileExists() {

        var request = new CustomerCreateRequest(
                "Ashok",
                "9876543210",
                "ABCDE1234F",
                null,
                null);

        when(repository.existsByMobile("9876543210"))
                .thenReturn(true);

        assertThrows(
                DuplicateCustomerException.class,
                () -> service.create(request)
        );
    }

    @Test
    void shouldThrowNotFound() {

        when(repository.findById(99L))
                .thenReturn(Optional.empty());

        assertThrows(
                CustomerNotFoundException.class,
                () -> service.getById(99L)
        );
    }
}
