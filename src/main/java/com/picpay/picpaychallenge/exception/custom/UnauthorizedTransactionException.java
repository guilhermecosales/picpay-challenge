package com.picpay.picpaychallenge.exception.custom;

public class UnauthorizedTransactionException extends RuntimeException {

    public UnauthorizedTransactionException(String message) {
        super(message);
    }

}
