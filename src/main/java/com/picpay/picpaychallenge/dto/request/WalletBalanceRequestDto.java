package com.picpay.picpaychallenge.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

@JsonIgnoreProperties(ignoreUnknown = true)
public record WalletBalanceRequestDto(

        @Positive
        BigDecimal balance

) {
}
