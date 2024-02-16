package com.picpay.picpaychallenge.repository;

import com.picpay.picpaychallenge.entity.Document;
import com.picpay.picpaychallenge.entity.User;
import com.picpay.picpaychallenge.enumerated.UserType;
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

    private final String existingEmail = "carolina.herrera@icloud.com";
    private final String existingCpf = "28731273840";
    private final String existingCnpj = "59111763000152";

    @BeforeEach
    void setUp() {
        Document document = new Document(existingCpf, existingCnpj);
        User user = new User();
        user.setFirstName("Carolina");
        user.setLastName("Herrera");
        user.setEmail(existingEmail);
        user.setPassword("$2a$12$pz1mvNBqvvLddst.z.RZOubmyP8F4AnjpV6Xmjl6zOsHuaXC1qmYm");
        user.setDocument(document);
        user.setUserType(UserType.COMMON);
        testEntityManager.persistAndFlush(user);
    }

    @Test
    void testExistsByEmail_whenGivenExistingEmail_returnsTrue() {
        boolean isExisting = userRepository.existsByEmail(existingEmail);
        Assertions.assertTrue(isExisting);
    }

    @Test
    void testExistsByDocumentCpf_whenGivenExistingCpf_returnsTrue() {
        boolean isExisting = userRepository.existsByDocumentCpf(existingCpf);
        Assertions.assertTrue(isExisting);
    }

    @Test
    void testExistsByDocumentCnpj_whenGivenExistingCnpj_returnsTrue() {
        boolean isExisting = userRepository.existsByDocumentCnpj(existingCnpj);
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