package com.picpay.picpaychallenge.factory;

import com.picpay.picpaychallenge.entity.Wallet;

public class WalletFactory {

    public static Wallet createWalletWithoutUser() {
        return new Wallet();
    }

}
