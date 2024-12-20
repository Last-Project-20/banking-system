package com.msa.banking.account.presentation.dto.directDebit;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DirectDebitRequestDto {

    @NotBlank(message="계좌 번호는 필수 입력 사항입니다.")
    @Pattern(regexp = "\\d{3}-\\d{4}-\\d{7}", message = "계좌 번호를 xxx-xxxx-xxxxxxx 형식에 맞게 입력해주세요.")
    private String beneficiaryAccount;

    @NotBlank(message="이체 금액은 필수 입력 사항입니다.")
    @DecimalMin(value = "0.01", message = "이체 금액은 0보다 커야 합니다.")
    @DecimalMax(value = "50000000.00", message = "이체 금액은 최대 50,000,000원을 넘을 수 없습니다.")
    private BigDecimal amount;

    @NotBlank(message="자동 이체 일자는 필수 입력 사항입니다.")
    private Integer transferDate;
}