package com.picpay.picpaychallenge.service;

import com.picpay.picpaychallenge.entity.Wallet;
import com.picpay.picpaychallenge.exception.custom.WalletException;
import com.picpay.picpaychallenge.repository.WalletRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class WalletService {

    private final WalletRepository walletRepository;

    @Transactional(readOnly = true)
    public Wallet findByUserId(Long userId) {
        return walletRepository.findByUserId(userId)
                .orElseThrow(() -> new WalletException("Wallet not found for user with ID: " + userId));
    }

    @Transactional
    public Wallet addBalance(Long walletId, BigDecimal amount) {
        try {
            Wallet wallet = walletRepository.getReferenceById(walletId);
            wallet.setBalance(wallet.getBalance().add(amount));
            return walletRepository.save(wallet);
        } catch (EntityNotFoundException e) {
            log.info("Error adding balance to Wallet: ", e);
            throw new WalletException("Wallet wit ID " + walletId + " not found.");
        }
    }

    @Transactional
    public void updateBalances(Long payerId, Long payeeId, BigDecimal transactionAmount) {
        Wallet payerWallet = walletRepository.getReferenceByUserId(payerId);
        Wallet payeeWallet = walletRepository.getReferenceByUserId(payeeId);

        payerWallet.setBalance(payerWallet.getBalance().subtract(transactionAmount));
        payeeWallet.setBalance(payeeWallet.getBalance().add(transactionAmount));

        walletRepository.saveAll(List.of(payerWallet, payeeWallet));
    }

}
