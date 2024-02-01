package com.picpay.picpaychallenge.service;

import com.picpay.picpaychallenge.entity.User;
import com.picpay.picpaychallenge.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public User save(User newUser) {
        validateIfDocumentExist(newUser.getDocument());
        validateIfEmailExist(newUser.getEmail());

        return userRepository.saveAndFlush(newUser);
    }

    private void validateIfDocumentExist(String document) {
        if (userRepository.existsByDocument(document)) {
            throw new RuntimeException("CPF/CNPJ already registered in the system.");
        }
    }

    private void validateIfEmailExist(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new RuntimeException("Email already registered in the system.");
        }
    }

}
