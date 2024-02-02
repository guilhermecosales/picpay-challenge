package com.picpay.picpaychallenge.dto.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public record TransactionDto(
        Long id,
        BigDecimal amount,
        Long payerId,
        Long payeeId,
        LocalDateTime timestamp
) implements Serializable {
}