package com.bank.customer.controller;

import com.bank.customer.dto.ApiResult;
import com.bank.customer.dto.CustomerCreateRequest;
import com.bank.customer.dto.SuccessResult;
import com.bank.customer.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<ApiResult> create(
            @RequestBody @Valid CustomerCreateRequest request) {

        var response = customerService.create(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new SuccessResult(
                        "Customer created",
                        response));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResult> get(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                new SuccessResult(
                        "Customer fetched",
                        customerService.getById(id)));
    }
}
