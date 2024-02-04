package com.picpay.picpaychallenge.mapper;

import com.picpay.picpaychallenge.dto.common.TransactionDto;
import com.picpay.picpaychallenge.dto.request.TransactionRequestDto;
import com.picpay.picpaychallenge.entity.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TransactionMapper {

    @Mapping(source = "payerId", target = "payer.id")
    @Mapping(source = "payeeId", target = "payee.id")
    Transaction transactionRequestDtoToTransaction(TransactionRequestDto from);

    @Mapping(source = "payer.id", target = "payerId")
    @Mapping(source = "payee.id", target = "payeeId")
    TransactionDto transactionToTransactionDto(Transaction from);

}
