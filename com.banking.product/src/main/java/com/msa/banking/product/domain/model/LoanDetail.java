package com.msa.banking.product.domain.model;

import com.msa.banking.common.base.AuditEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import javax.management.ObjectName;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Getter
@Builder(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "p_loan_detail")
public class LoanDetail extends AuditEntity {
    @Id
    @UuidGenerator
    @Column(name = "loan_detail_id")
    private UUID id;

    @Column(precision = 5, scale = 4, name = "interest_rate", nullable = false)
    private BigDecimal interestRate;

    @Column(name = "min_amount", nullable = false)
    private Long minAmount;

    @Column(name = "max_amount", nullable = false)
    private Long maxAmount;

    @Column(name = "loan_term", nullable = false)
    private int loanTerm;

    @Column(columnDefinition = "TEXT", name = "preferential_interest_rates", nullable = false)
    private String preferentialInterestRates;

    @Column(columnDefinition = "TEXT", name = "loan_detail", nullable = false)
    private String loanDetail;

    @Column(columnDefinition = "TEXT", name = "terms_and_conditions", nullable = false)
    private String termsAndConditions;

    //////////////////////////////////////////////////////////////////////////////////////

    @OneToOne(mappedBy = "loanDetail", fetch = FetchType.LAZY)
    private Product product;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "pdf_info_id", referencedColumnName = "pdf_info_id")
    private PDFInfo pdfInfo;

    /////////////////////////////////////////////////////////
    public static LoanDetail create(BigDecimal interestRate, Long minAmount, Long maxAmount,
                                    int loanTerm, String preferentialInterestRates,
                                    String loanDetail, String termsAndConditions){
        return LoanDetail.builder( )
                .interestRate(interestRate)
                .minAmount(minAmount)
                .maxAmount(maxAmount)
                .loanTerm(loanTerm)
                .preferentialInterestRates(preferentialInterestRates)
                .loanDetail(loanDetail)
                .termsAndConditions(termsAndConditions)
                .build();
    }
    public LoanDetail addPDF(PDFInfo pdfInfo) {
        this.pdfInfo = pdfInfo;
        return this;
    }

}
