package com.bank.customer.exception;

import com.bank.customer.dto.ErrorResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(DuplicateCustomerException.class)
    public ResponseEntity<ErrorResult> duplicate(
            DuplicateCustomerException ex) {

        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ErrorResult(
                        ex.getMessage(), 409));
    }

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<ErrorResult> notFound(
            CustomerNotFoundException ex) {

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorResult(
                        ex.getMessage(), 404));
    }
}
