package com.picpay.picpaychallenge.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.picpay.picpaychallenge.enumerated.UserType;

import java.io.Serializable;
import java.math.BigDecimal;

@JsonIgnoreProperties(ignoreUnknown = true)
public record UserRequestDto(
        String firstName,
        String lastName,
        String document,
        String email,
        String password,
        UserType userType,
        BigDecimal balance
) implements Serializable {
}