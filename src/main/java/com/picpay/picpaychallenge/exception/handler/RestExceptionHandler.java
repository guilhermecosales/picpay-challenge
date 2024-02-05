package com.picpay.picpaychallenge.exception.handler;

import com.picpay.picpaychallenge.exception.custom.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {
            DuplicateDocumentException.class,
            DuplicateEmailException.class,
            InsufficientBalanceException.class,
            MerchantTransactionException.class,
            UnauthorizedTransactionException.class
    })
    protected ResponseEntity<Object> handleCustomException(RuntimeException ex) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(status, ex.getMessage());
        return ResponseEntity.status(status).body(problemDetail);
    }

}
