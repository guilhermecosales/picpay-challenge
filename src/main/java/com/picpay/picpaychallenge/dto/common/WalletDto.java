package com.picpay.picpaychallenge.dto.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.math.BigDecimal;

@JsonIgnoreProperties(ignoreUnknown = true)
public record WalletDto(

        BigDecimal balance

) implements Serializable {
}