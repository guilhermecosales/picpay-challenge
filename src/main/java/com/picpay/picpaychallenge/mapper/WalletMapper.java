package com.picpay.picpaychallenge.mapper;

import com.picpay.picpaychallenge.dto.common.WalletDto;
import com.picpay.picpaychallenge.entity.Wallet;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WalletMapper {

    WalletDto walletToWalletDto(Wallet from);

}
