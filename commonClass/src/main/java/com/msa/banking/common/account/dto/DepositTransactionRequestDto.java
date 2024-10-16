package com.msa.banking.common.account.dto;

import com.msa.banking.common.account.type.TransactionType;

import java.math.BigDecimal;

public record DepositTransactionRequestDto(

        String accountNumber,
        TransactionType type,
        BigDecimal depositAmount,
        String description
) {
}
