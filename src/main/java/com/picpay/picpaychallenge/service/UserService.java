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
        return userRepository.saveAndFlush(newUser);
    }

}
