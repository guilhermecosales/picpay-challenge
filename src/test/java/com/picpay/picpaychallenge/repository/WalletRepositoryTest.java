package com.picpay.picpaychallenge.repository;

import com.picpay.picpaychallenge.entity.User;
import com.picpay.picpaychallenge.entity.Wallet;
import com.picpay.picpaychallenge.factory.UserFactory;
import com.picpay.picpaychallenge.factory.WalletFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;

@DataJpaTest
class WalletRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private WalletRepository walletRepository;

    private User user;

    @BeforeEach
    void setUp() {
        user = UserFactory.createCommonUserWithoutWallet();
        Wallet wallet = WalletFactory.createWalletWithoutUser();

        user.setWallet(wallet);
        wallet.setUser(user);
    }

    @Test
    void testFindByUserId_whenGivenExistingUserId_thenReturnOptionalWallet() {
        User storedUser = testEntityManager.persistAndFlush(user);

        Optional<Wallet> optionalWallet = walletRepository.findByUserId(storedUser.getId());

        Assertions.assertTrue(optionalWallet.isPresent());
    }

    @Test
    void testFindByUserId_whenGivenNonExistentUserId_thenReturnEmptyOptionalWallet() {
        Optional<Wallet> optionalUser = walletRepository.findByUserId(1L);
        Assertions.assertTrue(optionalUser.isEmpty());
    }

}