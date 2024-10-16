package com.msa.banking.account.domain.model;

import com.msa.banking.account.infrastructure.encryption.EncryptAttributeConverter;
import com.msa.banking.account.presentation.dto.directDebit.DirectDebitRequestDto;
import com.msa.banking.common.base.AuditEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Entity
@Table(name = "p_direct_debit")
@AllArgsConstructor
@NoArgsConstructor
@Builder(access = AccessLevel.PRIVATE)
public class DirectDebit extends AuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID directDebitId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "originating_account", referencedColumnName = "account_number")
    private Account account;

    @Convert(converter = EncryptAttributeConverter.class)
    @Column(nullable = false)
    @Pattern(regexp = "\\d{3}-\\d{4}-\\d{7}", message = "계좌번호는 xxx-xxxx-xxxxxxx 형식을 따라야 합니다.")
    private String beneficiaryAccount;

    @Column(precision = 15, scale = 2, nullable = false)
    private BigDecimal amount;

    // TODO: 날짜 검증 로직 필요
    @Column(nullable = false)
    private Integer transferDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Builder.Default
    private DirectDebitStatus status = DirectDebitStatus.ACTIVE;

    public static DirectDebit createDirectDebit(Account account, DirectDebitRequestDto requestDto) {
        return DirectDebit.builder()
                .account(account)
                .beneficiaryAccount(requestDto.beneficiaryAccount())
                .amount(requestDto.amount())
                .transferDate(requestDto.transferDate())
                .build();
    }

    // 이체 금액, 이체 날짜, 상태 변경 가능
    public void updateDirectDebit(DirectDebitRequestDto requestDto) {
        this.amount = requestDto.amount();
        this.transferDate = requestDto.transferDate();
    }

    // 이체 금액, 이체 날짜, 상태 변경 가능
    public void updateDirectDebitStatus(DirectDebitStatus status) {
        this.status = status;
    }
}
