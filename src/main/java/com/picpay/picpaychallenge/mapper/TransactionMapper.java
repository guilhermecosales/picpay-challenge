package com.picpay.picpaychallenge.mapper;

import com.picpay.picpaychallenge.dto.common.TransactionDto;
import com.picpay.picpaychallenge.dto.request.TransactionRequestDto;
import com.picpay.picpaychallenge.entity.Transaction;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TransactionMapper {

    Transaction transactionRequestDtoToTransaction(TransactionRequestDto from);

    TransactionDto transactionToTransactionDto(Transaction from);

}
