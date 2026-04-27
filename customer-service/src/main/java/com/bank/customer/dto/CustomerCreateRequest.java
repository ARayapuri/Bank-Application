package com.bank.customer.dto;

import jakarta.validation.constraints.NotBlank;

public record CustomerCreateRequest(@NotBlank String fullName,
                                    @NotBlank String mobile,
                                    @NotBlank String email,
                                    @NotBlank String pan,
                                    @NotBlank String address) {

}
