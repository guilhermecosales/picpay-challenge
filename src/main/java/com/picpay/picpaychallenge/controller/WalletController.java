package com.picpay.picpaychallenge.controller;

import com.picpay.picpaychallenge.dto.common.WalletDto;
import com.picpay.picpaychallenge.dto.request.WalletBalanceRequestDto;
import com.picpay.picpaychallenge.entity.Wallet;
import com.picpay.picpaychallenge.mapper.WalletMapper;
import com.picpay.picpaychallenge.service.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "api/wallets")
public class WalletController {

    private final WalletService walletService;
    private final WalletMapper walletMapper;

    @PutMapping(path = "{walletId}/balance")
    public ResponseEntity<WalletDto> addBalance(@PathVariable(name = "walletId") Long walletId, @RequestBody WalletBalanceRequestDto request) {
        Wallet walletWithBalanceUpdated = walletService.addBalance(walletId, request.balance());
        WalletDto response = walletMapper.walletToWalletDto(walletWithBalanceUpdated);
        return ResponseEntity.ok().body(response);
    }

}
