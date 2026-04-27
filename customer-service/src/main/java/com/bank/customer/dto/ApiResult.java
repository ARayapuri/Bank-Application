package com.bank.customer.dto;

public sealed interface ApiResult
       permits SuccessResult, ErrorResult{
}
