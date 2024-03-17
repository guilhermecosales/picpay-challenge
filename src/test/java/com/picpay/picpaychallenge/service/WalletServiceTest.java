package com.picpay.picpaychallenge.service;

import com.picpay.picpaychallenge.entity.Wallet;
import com.picpay.picpaychallenge.exception.custom.WalletException;
import com.picpay.picpaychallenge.repository.WalletRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class WalletServiceTest {

    @Mock
    private WalletRepository walletRepository;

    @InjectMocks
    private WalletService walletService;

    @Test
    void testFindByUserId_whenGivenExistingUserId_returnWallet() {
        long walletId = 1L;
        long userId = 1L;

        Wallet wallet = new Wallet();
        wallet.setId(walletId);

        when(walletRepository.findByUserId(anyLong())).thenReturn(Optional.of(wallet));

        Wallet storedWallet = walletService.findByUserId(userId);

        Assertions.assertNotNull(storedWallet);
        Mockito.verify(walletRepository, times(1)).findByUserId(userId);
    }

    @Test
    void testFindByUserId_whenGivenNotExistingUserId_throwEntityNotFoundException() {
        long userId = 1L;

        when(walletRepository.findByUserId(anyLong())).thenThrow(EntityNotFoundException.class);

        Assertions.assertThrows(WalletException.class, () -> walletService.findByUserId(userId));
        Mockito.verify(walletRepository, times(1)).findByUserId(anyLong());
    }

    @Test
    void testAddBalance_whenGivenValidData_returnWalletWithBalanceUpdated() {
        long walletId = 1L;
        BigDecimal amountToAdd = new BigDecimal("50.00");
        BigDecimal initialBalance = new BigDecimal("100.00");

        Wallet wallet = new Wallet();
        wallet.setId(walletId);
        wallet.setBalance(initialBalance);

        when(walletRepository.getReferenceById(walletId)).thenReturn(wallet);
        when(walletRepository.save(any(Wallet.class))).thenReturn(wallet);

        Wallet result = walletService.addBalance(walletId, amountToAdd);

        Assertions.assertEquals(walletId, result.getId());
        Assertions.assertEquals(initialBalance.add(amountToAdd), result.getBalance());
    }

    @Test
    void testAddBalance_whenGivenNotExistingWalletId_throwWalletException() {
        long walletId = 1L;
        BigDecimal amountToAdd = new BigDecimal("50.00");
        BigDecimal initialBalance = new BigDecimal("100.00");

        Wallet wallet = new Wallet();
        wallet.setId(walletId);
        wallet.setBalance(initialBalance);

        when(walletRepository.getReferenceById(walletId)).thenThrow(WalletException.class);

        Assertions.assertThrows(WalletException.class, () -> walletService.addBalance(walletId, amountToAdd));
    }

    @Test
    void testUpdateBalances_whenGivenValidData_returnVoid() {
        long payerId = 1L;
        long payerWalletId = 1L;
        long payeeId = 2L;
        long payeeWalletId = 2L;
        BigDecimal transactionAmount = new BigDecimal("50.00");

        Wallet payerWallet = new Wallet();
        payerWallet.setId(payerWalletId);
        payerWallet.setBalance(new BigDecimal("100.00"));

        Wallet payeeWallet = new Wallet();
        payeeWallet.setId(payeeWalletId);
        payeeWallet.setBalance(new BigDecimal("200.00"));

        when(walletRepository.getReferenceByUserId(payerWalletId)).thenReturn(payerWallet);
        when(walletRepository.getReferenceByUserId(payeeWalletId)).thenReturn(payeeWallet);
        when(walletRepository.saveAll(anyList())).thenReturn(List.of(payerWallet, payeeWallet));

        walletService.updateBalances(payerId, payeeId, transactionAmount);

        BigDecimal expectedPayerBalance = new BigDecimal("50.00");
        BigDecimal expectedPayeeBalance = new BigDecimal("250.00");

        Assertions.assertEquals(payerWallet.getBalance(), expectedPayerBalance);
        Assertions.assertEquals(payeeWallet.getBalance(), expectedPayeeBalance);
        Mockito.verify(walletRepository, times(2)).getReferenceByUserId(anyLong());
        Mockito.verify(walletRepository, times(1)).saveAll(anyList());
    }

}