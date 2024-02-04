package com.picpay.picpaychallenge.controller;

import com.picpay.picpaychallenge.dto.common.TransactionDto;
import com.picpay.picpaychallenge.dto.request.TransactionRequestDto;
import com.picpay.picpaychallenge.entity.Transaction;
import com.picpay.picpaychallenge.mapper.TransactionMapper;
import com.picpay.picpaychallenge.service.TransactionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "api/transactions")
public class TransactionController {

    private final TransactionService transactionService;
    private final TransactionMapper transactionMapper;

    @PostMapping
    public ResponseEntity<TransactionDto> makeTransaction(@RequestBody @Valid TransactionRequestDto request) {
        Transaction newTransaction = transactionMapper.transactionRequestDtoToTransaction(request);
        newTransaction = transactionService.makeTransaction(newTransaction);
        TransactionDto response = transactionMapper.transactionToTransactionDto(newTransaction);
        return ResponseEntity.ok().body(response);
    }

}
