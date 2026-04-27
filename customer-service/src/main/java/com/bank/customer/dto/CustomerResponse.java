package com.bank.customer.dto;

public record CustomerResponse(
        Long id,
        String fullName,
        String mobile,
        String email,
        String pan,
        String address) {
}
