package com.msa.banking.account.presentation.dto;

import com.msa.banking.account.domain.model.AccountStatus;
import com.msa.banking.account.domain.model.AccountType;

public record AccountRequestDto(

        String accountHolder,
        AccountStatus status,
        AccountType type
) {
}
