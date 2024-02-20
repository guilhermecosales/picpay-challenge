package com.picpay.picpaychallenge.repository;

import com.picpay.picpaychallenge.entity.User;
import com.picpay.picpaychallenge.factory.UserFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private UserRepository userRepository;

    private User user;

    @BeforeEach
    void setUp() {
        user = UserFactory.createCommonUserWithoutWallet();
        testEntityManager.persistAndFlush(user);
    }

    @Test
    void testExistsByEmail_whenGivenExistingEmail_returnsTrue() {
        boolean isExisting = userRepository.existsByEmail(user.getEmail());
        Assertions.assertTrue(isExisting);
    }

    @Test
    void testExistsByDocumentCpf_whenGivenExistingCpf_returnsTrue() {
        boolean isExisting = userRepository.existsByDocumentCpf(user.getDocument().getCpf());
        Assertions.assertTrue(isExisting);
    }

    @Test
    void testExistsByDocumentCnpj_whenGivenExistingCnpj_returnsTrue() {
        boolean isExisting = userRepository.existsByDocumentCnpj(user.getDocument().getCnpj());
        Assertions.assertTrue(isExisting);
    }

    @Test
    void testExistsByEmail_whenGivenNonExistentEmail_returnsTrue() {
        String nonExistentEmail = "marjorie.herrera@icloud.com";
        boolean isExisting = userRepository.existsByEmail(nonExistentEmail);
        Assertions.assertFalse(isExisting);
    }

    @Test
    void testExistsByDocumentCpf_whenGivenNonExistentCpf_returnsTrue() {
        String nonExistentCpf = "99147500875";
        boolean isExisting = userRepository.existsByDocumentCpf(nonExistentCpf);
        Assertions.assertFalse(isExisting);
    }

    @Test
    void testExistsByDocumentCnpj_whenGivenNonExistentCnpj_returnsTrue() {
        String nonExistentCnpj = "17779059000122";
        boolean isExisting = userRepository.existsByDocumentCnpj(nonExistentCnpj);
        Assertions.assertFalse(isExisting);
    }

}