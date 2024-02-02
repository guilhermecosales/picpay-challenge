package com.picpay.picpaychallenge.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

@JsonIgnoreProperties(ignoreUnknown = true)
public record TransactionRequestDto(
        @NotNull
        @PositiveOrZero
        BigDecimal amount,

        @NotNull
        @Positive
        Long payerId,

        @Positive
        Long payeeId
) {
}
