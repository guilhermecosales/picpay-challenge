package com.picpay.picpaychallenge.service;

import com.picpay.picpaychallenge.entity.User;
import com.picpay.picpaychallenge.exception.custom.DuplicateDocumentException;
import com.picpay.picpaychallenge.exception.custom.DuplicateEmailException;
import com.picpay.picpaychallenge.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
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
            throw new DuplicateDocumentException("CPF/CNPJ already registered in the system.");
        }
    }

    private void validateIfEmailExist(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new DuplicateEmailException("Email already registered in the system.");
        }
    }

    @Transactional(readOnly = true)
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found."));
    }

}
