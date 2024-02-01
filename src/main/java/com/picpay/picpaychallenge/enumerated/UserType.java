package com.picpay.picpaychallenge.enumerated;

public enum UserType {

    COMMON,
    MERCHANT;

    public boolean isMerchant() {
        return this == MERCHANT;
    }

}
