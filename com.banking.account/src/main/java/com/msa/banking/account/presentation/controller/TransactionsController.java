package com.msa.banking.account.presentation.controller;

import com.msa.banking.account.application.service.TransactionsService;
import com.msa.banking.account.presentation.dto.transactions.TransactionResponseDto;
import com.msa.banking.account.presentation.dto.transactions.TransactionsListResponseDto;
import com.msa.banking.account.presentation.dto.transactions.TransactionsSearchRequestDto;
import com.msa.banking.commonbean.security.UserDetailsImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/account-transactions")
public class TransactionsController {

    private final TransactionsService transactionsService;

    public TransactionsController(TransactionsService transactionsService) {
        this.transactionsService = transactionsService;
    }


    // 거래 설명 수정
    @PatchMapping("/{transaction_id}")
    @PreAuthorize("hasAnyAuthority('MASTER', 'MANAGER', 'CUSTOMER')")
    public ResponseEntity<TransactionResponseDto> updateTransaction(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @PathVariable("transaction_id") Long transactionId,
            @RequestParam String description) {

        return ResponseEntity.ok(transactionsService.updateTransaction(transactionId, description,userDetails.getUsername(), userDetails.getRole()));
    }


    // 거래 전체 조회
    @GetMapping
    @PreAuthorize("hasAnyAuthority('MASTER', 'MANAGER')")
    public ResponseEntity<Page<TransactionsListResponseDto>> getTransactions(
            @RequestParam(defaultValue = "10") int page,
            @RequestParam(defaultValue = "1") int size,
            @RequestBody TransactionsSearchRequestDto search) {

        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(transactionsService.getTransactions(search, pageable));
    }

    // 거래 상세 조회
    @GetMapping("/{transaction_id}")
    @PreAuthorize("hasAnyAuthority('MASTER', 'MANAGER', 'CUSTOMER')")
    public ResponseEntity<TransactionResponseDto> getTransaction(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @PathVariable("transaction_id") Long transactionId) {

        return ResponseEntity.ok(transactionsService.getTransaction(transactionId, userDetails.getUsername(), userDetails.getRole()));
    }
}