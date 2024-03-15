package com.picpay.picpaychallenge.service;

import com.picpay.picpaychallenge.entity.Document;
import com.picpay.picpaychallenge.entity.User;
import com.picpay.picpaychallenge.entity.Wallet;
import com.picpay.picpaychallenge.enumerated.UserType;
import com.picpay.picpaychallenge.exception.custom.DuplicateDocumentException;
import com.picpay.picpaychallenge.exception.custom.DuplicateEmailException;
import com.picpay.picpaychallenge.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    private User newUser;
    private User savedUser;

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

        savedUser = new User();
        savedUser.setId(1L);
        savedUser.setFirstName("Carolina");
        savedUser.setLastName("Herrera");
        savedUser.setDocument(document);
        savedUser.setEmail("carolina.herrera@icloud.com");
        savedUser.setPassword("$2a$12$4mWre8utnQD.vYfSBAw7Ku4dUxtEeMqn03HYTwWhaiyX2gbFPfDFe");
        savedUser.setUserType(UserType.COMMON);
        savedUser.setCreatedAt(LocalDateTime.now());
        savedUser.setUpdatedAt(LocalDateTime.now());

        Wallet savedWallet = new Wallet();
        savedWallet.setId(1L);
        savedWallet.setBalance(BigDecimal.valueOf(0.0));
        savedWallet.setCreatedAt(LocalDateTime.now());
        savedWallet.setUpdatedAt(LocalDateTime.now());

        savedUser.setWallet(savedWallet);
        savedWallet.setUser(savedUser);

    }

    @Test
    void testSave_whenGivenValidUserData_returnStoredUser() {
        when(passwordEncoder.encode(any(CharSequence.class))).thenReturn(savedUser.getPassword());
        when(userRepository.save(any(User.class))).thenReturn(savedUser);

        User storedUser = userService.save(newUser);

        Assertions.assertEquals(savedUser, storedUser);
        Mockito.verify(userRepository, times(1)).save(newUser);
    }

    @Test
    void testSave_whenCPFAlreadyExists_throwDuplicateDocumentException() {
        when(userRepository.existsByDocumentCpf(anyString())).thenReturn(true);

        Assertions.assertThrows(DuplicateDocumentException.class, () -> userService.save(newUser));
        Mockito.verify(userRepository, never()).save(newUser);
    }

    @Test
    void testSave_whenCNPJAlreadyExists_throwDuplicateDocumentException() {
        when(userRepository.existsByDocumentCnpj(anyString())).thenReturn(true);

        Assertions.assertThrows(DuplicateDocumentException.class, () -> userService.save(newUser));
        Mockito.verify(userRepository, never()).save(newUser);
    }

    @Test
    void testSave_whenEmailAlreadyExists_throwDuplicateEmailException() {
        when(userRepository.existsByEmail(anyString())).thenReturn(true);

        Assertions.assertThrows(DuplicateEmailException.class, () -> userService.save(newUser));
        Mockito.verify(userRepository, never()).save(newUser);
    }

    @Test
    void testFindById_whenGivenValidUserId_returnStoredUser() {
        when(userRepository.findById(any(Long.class))).thenReturn(Optional.of(savedUser));
        User foundUser = userService.findById(savedUser.getId());
        Assertions.assertEquals(savedUser, foundUser);
    }

    @Test
    void testFindById_whenGivenInvalidUserId_throwEntityNotFoundException() {
        when(userRepository.findById(any(Long.class))).thenThrow(EntityNotFoundException.class);
        Assertions.assertThrows(EntityNotFoundException.class, () -> userService.findById(10L));
    }

}