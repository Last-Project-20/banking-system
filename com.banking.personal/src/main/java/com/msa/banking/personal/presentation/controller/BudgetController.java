package com.msa.banking.personal.presentation.controller;

import com.msa.banking.common.response.SuccessCode;
import com.msa.banking.common.response.SuccessResponse;
import com.msa.banking.commonbean.security.UserDetailsImpl;
import com.msa.banking.personal.application.dto.budget.BudgetListDto;
import com.msa.banking.personal.application.dto.budget.BudgetRequestDto;
import com.msa.banking.personal.application.dto.budget.BudgetResponseDto;
import com.msa.banking.personal.application.dto.budget.BudgetUpdateDto;
import com.msa.banking.personal.application.service.BudgetService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/budgets")
@Log4j2(topic = "BudgetController")
@Tag(name = "예산 서비스", description = "예산을 설정하고 수정할 수 있는 API 입니다.")
public class BudgetController {

    private final BudgetService budgetService;

    /**
     * 예산 설정 목록 조회
     * @param pageable 
     * @return
     */
    @GetMapping("")
    @PreAuthorize("hasAnyAuthority('MASTER', 'MANAGER', 'CUSTOMER')")
    @Operation(summary = "설정한 예산 목록 조회", description = "설정한 예산 목록 조회 API 입니다.", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<?> getBudgetList(Pageable pageable, @AuthenticationPrincipal UserDetailsImpl userDetails) {

        UUID userId = userDetails.getUserId();
        String userRole = userDetails.getRole();
        Page<BudgetListDto> budgetListPage = budgetService.getBudgetList(pageable, userId, userRole);

        return ResponseEntity.ok(
                new SuccessResponse<>(SuccessCode.SELECT_SUCCESS.getStatus(), "getBudgetList", budgetListPage));
    }

    /**
     * 예산 설정 단 건 조회
     * @param budgetId 
     * @param userDetails
     * @return
     */
    @GetMapping("/{budget_id}")
    @PreAuthorize("hasAnyAuthority('MASTER', 'MANAGER', 'CUSTOMER')")
    @Operation(summary = "설정한 예산 단 건 조회", description = "설정한 예산 단 건 조회 API 입니다.", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<?> findBudgetById(@PathVariable("budget_id") UUID budgetId,
                                            @AuthenticationPrincipal UserDetailsImpl userDetails) {

        String userRole = userDetails.getRole();
        UUID userId = userDetails.getUserId();

        BudgetResponseDto responseDto = budgetService.findBudgetById(budgetId, userRole, userId);

        return ResponseEntity.ok(
                new SuccessResponse<>(SuccessCode.SELECT_SUCCESS.getStatus(), "findBudgetById", responseDto));

    }

    /**
     * 예산 설정 생성
     * @param budgetRequestDto 
     * @param userDetails
     * @return
     */
    @PostMapping("")
    @PreAuthorize("hasAnyAuthority('MASTER', 'CUSTOMER')")
    @Operation(summary = "예산 설정 생성", description = "예산을 설정하는 API 입니다.", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<?> createBudget(@Valid @RequestBody BudgetRequestDto budgetRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {

        UUID userId = userDetails.getUserId();
        String userName = userDetails.getUsername();
        String userRole = userDetails.getRole();

        log.info(budgetRequestDto);

        BudgetResponseDto responseDto = budgetService.createBudget(budgetRequestDto, userRole, userId, userName);

        return ResponseEntity.ok(
                new SuccessResponse<>(SuccessCode.INSERT_SUCCESS.getStatus(), "createBudget", responseDto));
    }

    /**
     * 설정한 총 예산, 기간 수정
     * @param budgetId 
     * @param budgetUpdateDto
     * @param userDetails
     * @return
     */
    @PatchMapping("/{budget_id}")
    @PreAuthorize("hasAnyAuthority('MASTER', 'CUSTOMER')")
    @Operation(summary = "설정한 예산, 기간 수정", description = "설정한 예산, 기간을 수정하는 API 입니다.", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<?> updateBudget(@PathVariable("budget_id") UUID budgetId, @RequestBody BudgetUpdateDto budgetUpdateDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {

        UUID userId = userDetails.getUserId();
        String userName = userDetails.getUsername();
        String userRole = userDetails.getRole();

        BudgetResponseDto responseDto = budgetService.updateBudget(budgetId, budgetUpdateDto, userRole, userId, userName);

        return ResponseEntity.ok(
                new SuccessResponse<>(SuccessCode.UPDATE_SUCCESS.getStatus(), "updateBudget", responseDto));
    }

    /**
     * 예산 설정 삭제(Soft Delete)
     * @param budgetId 
     * @param userDetails
     * @return
     */
    @DeleteMapping("/{budget_id}")
    @PreAuthorize("hasAnyAuthority('MASTER', 'CUSTOMER')")
    @Operation(summary = "예산 설정 삭제", description = "설정한 예산 삭제하는 API 입니다.", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<?> deleteBudget(@PathVariable("budget_id") UUID budgetId, @AuthenticationPrincipal UserDetailsImpl userDetails) {

        UUID userId = userDetails.getUserId();
        String userName = userDetails.getUsername();
        String userRole = userDetails.getRole();

        budgetService.deleteBudget(budgetId, userRole, userId, userName);

        return ResponseEntity.ok(
                new SuccessResponse<>(SuccessCode.DELETE_SUCCESS.getStatus(), "deleteBudget", "예산 설정이 삭제되었습니다."));
    }
}
