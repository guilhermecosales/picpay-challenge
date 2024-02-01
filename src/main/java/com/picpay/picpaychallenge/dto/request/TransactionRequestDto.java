package com.picpay.picpaychallenge.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;

@JsonIgnoreProperties(ignoreUnknown = true)
public record TransactionRequestDto(
        BigDecimal amount,
        Long payerId,
        Long payeeId
) {
}
