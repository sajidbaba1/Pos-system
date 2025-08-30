package com.example.possystem.repository;

import com.example.possystem.domain.Refund;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface RefundRepository extends JpaRepository<Refund, Long> {
    List<Refund> findByCashierId(Long cashierId);
    List<Refund> findByBranchId(Long branchId);

    // This is not in the prompt, but it's useful
    // List<Refund> findByShiftReportId(Long shiftReportId);

    @Query("SELECT r FROM Refund r WHERE r.cashier.id = :cashierId AND r.refundDate BETWEEN :startDate AND :endDate")
    List<Refund> findByCashierIdAndDateRange(@Param("cashierId") Long cashierId, @Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);
}
