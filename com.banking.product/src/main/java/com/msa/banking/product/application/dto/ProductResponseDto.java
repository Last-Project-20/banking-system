package com.msa.banking.product.application.dto;

import com.msa.banking.product.domain.ProductType;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder(access = AccessLevel.PRIVATE)
public class ProductResponseDto {
    private UUID id;
    private String name;
    private ProductType type;
    private LocalDateTime validFrom;
    private LocalDateTime validTo;
    private Boolean isFinish;
    private ProductDetailDto detail;
}
