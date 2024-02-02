package com.picpay.picpaychallenge.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.picpay.picpaychallenge.enumerated.UserType;
import jakarta.validation.constraints.*;

import java.io.Serializable;
import java.math.BigDecimal;

@JsonIgnoreProperties(ignoreUnknown = true)
public record UserRequestDto(
        @NotEmpty
        @Size(min = 3, max = 20)
        String firstName,

        @NotEmpty
        @Size(min = 3, max = 50)
        String lastName,

        String document,

        @Email
        @NotEmpty
        @Size(max = 60)
        String email,

        @NotEmpty
        String password,

        @NotNull
        UserType userType,

        @PositiveOrZero
        BigDecimal balance
) implements Serializable {
}