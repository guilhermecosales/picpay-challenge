package com.picpay.picpaychallenge.service;

import com.picpay.picpaychallenge.entity.Document;
import com.picpay.picpaychallenge.entity.User;
import com.picpay.picpaychallenge.entity.Wallet;
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
        validateNewUser(newUser);

        Wallet newWallet = new Wallet();
        newUser.setWallet(newWallet);
        newWallet.setUser(newUser);

        return userRepository.save(newUser);
    }

    private void validateNewUser(User newUser) {
        validateIfDocumentDoesNotExist(newUser.getDocument());
        validateIfEmailExist(newUser.getEmail());
    }

    private void validateIfDocumentDoesNotExist(Document document) {
        if (cpfExists(document) && document.getCpf() != null) {
            throw new DuplicateDocumentException("CPF already registered.");
        }

        if (cnpjExists(document) && document.getCnpj() != null) {
            throw new DuplicateDocumentException("CNPJ already registered.");
        }
    }

    private boolean cpfExists(Document document) {
        return userRepository.existsByDocumentCpf(document.getCpf());
    }

    private boolean cnpjExists(Document document) {
        return userRepository.existsByDocumentCnpj(document.getCnpj());
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
