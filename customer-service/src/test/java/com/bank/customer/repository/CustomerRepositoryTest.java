package com.bank.customer.repository;

import com.bank.customer.entity.Customer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@ActiveProfiles("test")
class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository repository;

    @Test
    @DisplayName("Should find customer by PAN")
    void shouldFindByPan() {

        Customer customer = Customer.builder()
                .fullName("Ashok")
                .mobile("9999999999")
                .email("ashok@gmail.com")
                .pan("ABCDE1234F")
                .address("Hyderabad")
                .build();

        repository.save(customer);

        Optional<Customer> result =
                repository.findByPan("ABCDE1234F");

        assertTrue(result.isPresent());
        assertEquals("Ashok", result.get().getFullName());
    }

    @Test
    @DisplayName("Should return true when mobile exists")
    void shouldExistsByMobile() {

        Customer customer = Customer.builder()
                .fullName("Ravi")
                .mobile("8888888888")
                .email("a@gmail.com")
                .pan("PQRSX1234K")
                .address("Hyderabad")
                .build();

        repository.save(customer);

        boolean exists =
                repository.existsByMobile("8888888888");

        assertTrue(exists);
    }

    @Test
    @DisplayName("Should return true when PAN exists")
    void shouldExistsByPan() {

        Customer customer = Customer.builder()
                .fullName("Kiran")
                .mobile("7777777777")
                .email("k@gmail.com")
                .pan("ZXCVB1234M")
                .address("Hyderabad")
                .build();

        repository.save(customer);

        boolean exists =
                repository.existsByPan("ZXCVB1234M");

        assertTrue(exists);
    }

    @Test
    @DisplayName("Should return empty when PAN not found")
    void shouldReturnEmptyWhenPanNotFound() {

        Optional<Customer> result =
                repository.findByPan("NOTFOUND123");

        assertTrue(result.isEmpty());
    }
}
