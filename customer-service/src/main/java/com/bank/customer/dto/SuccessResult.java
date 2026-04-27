package com.bank.customer.dto;

public record SuccessResult(
        String message,
        Object data
)implements ApiResult {
}
