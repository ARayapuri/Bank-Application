package com.bank.customer.dto;

public record ErrorResult(
        String message,
        int status
)implements ApiResult {
}
