package com.picpay.picpaychallenge.repository;

import com.picpay.picpaychallenge.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long> {

    Wallet getReferenceByUserId(Long userId);

    Optional<Wallet> findByUserId(Long userId);

}
