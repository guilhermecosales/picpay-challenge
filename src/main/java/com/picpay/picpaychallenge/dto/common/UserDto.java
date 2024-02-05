package com.picpay.picpaychallenge.dto.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.picpay.picpaychallenge.enumerated.UserType;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public record UserDto(
        Long id,
        String firstName,
        String lastName,
        String document,
        String email,
        UserType userType,
        WalletDto wallet
) implements Serializable {
}