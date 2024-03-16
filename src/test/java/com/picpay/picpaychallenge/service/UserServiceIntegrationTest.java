package com.picpay.picpaychallenge.service;

import com.picpay.picpaychallenge.entity.Document;
import com.picpay.picpaychallenge.entity.User;
import com.picpay.picpaychallenge.entity.Wallet;
import com.picpay.picpaychallenge.enumerated.UserType;
import com.picpay.picpaychallenge.exception.custom.DuplicateDocumentException;
import com.picpay.picpaychallenge.exception.custom.DuplicateEmailException;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ActiveProfiles(value = "test")
@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
class UserServiceIntegrationTest {

    @Autowired
    private UserService userService;

    private User newUser;

    @BeforeEach
    void setUp() {
        Document document = new Document("76599342019", "44125012000118");

        newUser = new User();
        newUser.setFirstName("Carolina");
        newUser.setLastName("Herrera");
        newUser.setDocument(document);
        newUser.setEmail("carolina.herrera@icloud.com");
        newUser.setPassword("herrera$767");
        newUser.setUserType(UserType.COMMON);
        newUser.setCreatedAt(LocalDateTime.now());
        newUser.setUpdatedAt(LocalDateTime.now());

        Wallet newWallet = new Wallet();
        newWallet.setBalance(BigDecimal.valueOf(0.0));
        newWallet.setCreatedAt(LocalDateTime.now());
        newWallet.setUpdatedAt(LocalDateTime.now());

        newUser.setWallet(newWallet);
        newWallet.setUser(newUser);
    }

    @Test
    @Order(1)
    void testSave_whenGivenValidUserData_returnStoredUser() {
        User storedUser = userService.save(newUser);

        Assertions.assertAll("storedUser",
                () -> assertNotNull(storedUser),
                () -> assertNotNull(storedUser.getId()),
                () -> assertNotNull(storedUser.getWallet())
        );
    }

    @Test
    void testSave_whenCPFAlreadyExists_throwDuplicateDocumentException() {
        Document documentWithCpfExisting = new Document("76599342019", "60514139000185");
        newUser.setDocument(documentWithCpfExisting);

        Exception exception = Assertions.assertThrows(DuplicateDocumentException.class, () -> userService.save(newUser));
        Assertions.assertEquals("CPF already registered.", exception.getMessage());
    }

    @Test
    void testSave_whenCNPJAlreadyExists_throwDuplicateDocumentException() {
        Document documentWithCnpjExisting = new Document("81620325098", "44125012000118");
        newUser.setDocument(documentWithCnpjExisting);

        Exception exception = Assertions.assertThrows(DuplicateDocumentException.class, () -> userService.save(newUser));
        Assertions.assertEquals("CNPJ already registered.", exception.getMessage());
    }

    @Test
    void testSave_whenEmailAlreadyExists_throwDuplicateEmailException() {
        Document newDocument = new Document("81620325098", "60514139000185");
        newUser.setDocument(newDocument);

        Exception exception = Assertions.assertThrows(DuplicateEmailException.class, () -> userService.save(newUser));
        Assertions.assertEquals("Email already registered in the system.", exception.getMessage());
    }

    @Test
    void testFindById_whenGivenValidUserId_returnStoredUser() {
        Long existingId = 1L;
        User foundUser = assertDoesNotThrow(() -> userService.findById(existingId));
        Assertions.assertNotNull(foundUser);
    }

    @Test
    void testFindById_whenGivenInvalidUserId_throwEntityNotFoundException() {
        Long existingId = 100L;
        Exception exception = Assertions.assertThrows(EntityNotFoundException.class, () -> userService.findById(existingId));
        Assertions.assertEquals("User not found.", exception.getMessage());
    }

}