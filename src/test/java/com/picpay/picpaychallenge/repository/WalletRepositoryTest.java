package com.picpay.picpaychallenge.repository;

import com.picpay.picpaychallenge.entity.Document;
import com.picpay.picpaychallenge.entity.User;
import com.picpay.picpaychallenge.entity.Wallet;
import com.picpay.picpaychallenge.enumerated.UserType;
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
    private static final Document document = new Document("76599342019", "44125012000118");
    private static final String hashedPassword = "$2a$12$4mWre8utnQD.vYfSBAw7Ku4dUxtEeMqn03HYTwWhaiyX2gbFPfDFe";

    @BeforeEach
    void setUp() {
        user = new User(
                null,
                "Carolina",
                "Herrera",
                document,
                "carolina.herrera@icloud.com",
                hashedPassword,
                UserType.COMMON,
                null,
                null,
                null
        );

        Wallet wallet = new Wallet();

        user.setWallet(wallet);
        wallet.setUser(user);
    }

    @Test
    void testGetReferenceByUserId_whenGivenExistingUserId_thenReturnWallet() {
        User storedUser = testEntityManager.persistAndFlush(user);
        Wallet wallet = walletRepository.getReferenceByUserId(storedUser.getId());
        Assertions.assertNotNull(wallet);
    }

    @Test
    void testGetReferenceByUserId_whenGivenNonExistentUserId_thenReturnNull() {
        Wallet wallet = walletRepository.getReferenceByUserId(2L);
        Assertions.assertNull(wallet);
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