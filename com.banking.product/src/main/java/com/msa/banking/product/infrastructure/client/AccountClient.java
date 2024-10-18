package com.msa.banking.product.infrastructure.client;

import com.msa.banking.common.account.dto.AccountRequestDto;
import com.msa.banking.common.account.dto.LoanDepositTransactionRequestDto;
import com.msa.banking.common.account.dto.SingleTransactionResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

@FeignClient(name = "AccountService")
public interface AccountClient {

    @PostMapping("/api/accounts")
    ResponseEntity<UUID> addAccount(@RequestBody AccountRequestDto accountRequestDto);

    @PostMapping("/api/account-transactions/{account_id}/loan-deposit")
    public ResponseEntity<SingleTransactionResponseDto> updateAccount(
            @PathVariable("account_id") UUID accountId,
            @RequestBody LoanDepositTransactionRequestDto request);
}
