package com.bank.customer.exception;

import com.bank.customer.controller.CustomerController;
import com.bank.customer.service.CustomerService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CustomerController.class)
@Import(GlobalExceptionHandler.class)
class GlobalExceptionHandlerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CustomerService service;

    @Test
    @DisplayName("Should return 409 when duplicate customer exists")
    void shouldReturn409WhenDuplicate() throws Exception {

        when(service.create(any()))
                .thenThrow(
                        new DuplicateCustomerException(
                                "Mobile already registered"));

        mockMvc.perform(post("/api/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                        {
                          "fullName":"Ashok",
                          "mobile":"9876543210",
                          "email":"a@gmail.com",
                          "pan":"ABCDE1234F",
                          "address":"Hyd"
                        }
                        """))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.message")
                        .value("Mobile already registered"))
                .andExpect(jsonPath("$.status")
                        .value(409));
    }

    @Test
    @DisplayName("Should return 404 when customer not found")
    void shouldReturn404WhenCustomerMissing() throws Exception {

        when(service.create(any()))
                .thenThrow(
                        new CustomerNotFoundException(
                                "Customer not found"));

        mockMvc.perform(post("/api/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                        {
                          "fullName":"Ashok",
                          "mobile":"9876543210",
                          "email":"a@gmail.com",
                          "pan":"ABCDE1234F",
                          "address":"Hyd"
                        }
                        """))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message")
                        .value("Customer not found"))
                .andExpect(jsonPath("$.status")
                        .value(404));
    }
}