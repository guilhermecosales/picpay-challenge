package com.picpay.picpaychallenge.service;

import com.picpay.picpaychallenge.entity.Transaction;
import com.picpay.picpaychallenge.entity.User;
import com.picpay.picpaychallenge.exception.custom.InsufficientBalanceException;
import com.picpay.picpaychallenge.exception.custom.MerchantTransactionException;
import com.picpay.picpaychallenge.exception.custom.UnauthorizedTransactionException;
import com.picpay.picpaychallenge.repository.TransactionRepository;
import com.picpay.picpaychallenge.service.client.AuthorizerService;
import com.picpay.picpaychallenge.service.client.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final UserService userService;

    private final AuthorizerService authorizerService;
    private final NotificationService notificationService;

    @Transactional
    public Transaction makeTransaction(Transaction newTransaction) {
        User payer = userService.findById(newTransaction.getPayer().getId());
        validateIfPayerIsMerchant(payer);
        validateBalance(payer, newTransaction.getAmount());

        User payee = userService.findById(newTransaction.getPayee().getId());

        authorizeTransaction(payer.getId(), payee.getId(), newTransaction.getAmount());

        Transaction transactionMade = transactionRepository.save(newTransaction);
        userService.updateBalances(transactionMade);

        notificationService.sendEmail(payee.getEmail());

        log.info("Transaction completed successfully. Payer: {}, Payee: {}, Amount: {}",
                payer.getId(), payee.getId(), transactionMade.getAmount());

        return transactionMade;
    }

    private void validateBalance(User sender, BigDecimal amount) {
        if (sender.getBalance().compareTo(amount) < 0) {
            throw new InsufficientBalanceException("Insufficient balance to make the transaction.");
        }
    }

    private void validateIfPayerIsMerchant(User payer) {
        if (payer.getUserType().isMerchant()) {
            throw new MerchantTransactionException("Merchant user " + payer.getId() + " cannot make transactions.");
        }
    }

    private void authorizeTransaction(Long payerId, Long payeeId, BigDecimal amount) {
        if (!authorizerService.isAuthorized(payerId, payeeId, amount)) {
            throw new UnauthorizedTransactionException("The transaction was not authorized for payerId: " + payerId +
                                                       ", payeeId: " + payeeId + ", amount: " + amount);
        }
    }

}
