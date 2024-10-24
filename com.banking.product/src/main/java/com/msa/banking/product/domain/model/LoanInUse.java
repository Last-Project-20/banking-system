package com.msa.banking.product.domain.model;

import com.msa.banking.common.base.AuditEntity;
import com.msa.banking.product.lib.LoanState;
import com.msa.banking.product.lib.ProductType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.math.BigDecimal;
import java.rmi.server.UID;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder(access = AccessLevel.PRIVATE)
public class LoanInUse extends AuditEntity {

    @Id
    @UuidGenerator
    @Column(name = "loan_in_use_id")
    private UUID id;

    @Column(name = "loan_amount", nullable = false)
    private Long loanAmount;

    @Column(precision = 6, scale = 4, name = "interest_rate",  nullable = false)
    private BigDecimal interestRate;

    @Column(name = "start_date", nullable = false)
    private LocalDateTime startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDateTime endDate;

    @Column(name = "reviewer", nullable = true)
    private UUID reviewer;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private LoanState status;

    ///////////////////////////////////////////////////////////////////////

    @OneToOne(mappedBy = "loanInUse", fetch = FetchType.LAZY)
    private UsingProduct usingProduct;

    ///////////////////////////////////////////////////////////////////////

    public static LoanInUse create(Long loanAmount, String name, BigDecimal interestRate, long month){
        return LoanInUse.builder()
                .loanAmount(loanAmount)
                .interestRate(interestRate)
                .startDate(LocalDateTime.now())
                .endDate(LocalDateTime.now().plusMonths(month))
                .reviewer(null)
                .status(LoanState.APPLY)
                .build();
    }
    public void approvalLoan(String name) {
        this.status = LoanState.BEFOREEXECUTION;
        super.setUpdateByUserName(name);
    }
    public void refusalLoan(String name) {
        this.status = LoanState.REFUASAL;
        super.setUpdateByUserName(name);
    }

    public void runLoan(String name) {
        this.status = LoanState.RUNNING;
        super.setUpdateByUserName(name);
    }

    public void cancleLoan() {
        this.status = LoanState.CANCLE;
    }
    public void reviewerUp(UUID id){
        this.reviewer = id;
    }





}
