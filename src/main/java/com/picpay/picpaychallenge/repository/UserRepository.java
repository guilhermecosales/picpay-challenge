package com.picpay.picpaychallenge.repository;

import com.picpay.picpaychallenge.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByDocument(String document);

    boolean existsByDocument(String document);

    boolean existsByEmail(String email);

}
