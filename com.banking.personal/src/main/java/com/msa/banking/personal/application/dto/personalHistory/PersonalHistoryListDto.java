package com.msa.banking.personal.application.dto.personalHistory;

import com.msa.banking.personal.domain.enums.PersonalHistoryType;
import com.msa.banking.personal.domain.model.PersonalHistory;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class PersonalHistoryListDto {

    private Long historyId;
    private String categoryName;
    private PersonalHistoryType type;
    private BigDecimal amount;
    private LocalDateTime transactionDate;

    public static PersonalHistoryListDto toDTO(PersonalHistory personalHistory){
        return PersonalHistoryListDto.builder()
                .historyId(personalHistory.getId())
                .categoryName(personalHistory.getCategory().getName())
                .type(personalHistory.getType())
                .amount(personalHistory.getAmount())
                .transactionDate(personalHistory.getTransactionDate())
                .build();
    }
}