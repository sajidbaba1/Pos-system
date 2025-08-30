package com.example.possystem.service;

import com.example.possystem.domain.Refund;
import com.example.possystem.repository.RefundRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class RefundService {

    @Autowired
    private RefundRepository refundRepository;

    public Refund createRefund(Refund refund) {
        refund.setRefundDate(LocalDateTime.now());
        return refundRepository.save(refund);
    }

    public List<Refund> getAllRefunds() {
        return refundRepository.findAll();
    }

    public Optional<Refund> getRefundById(Long id) {
        return refundRepository.findById(id);
    }

    public List<Refund> getRefundsByCashier(Long cashierId) {
        return refundRepository.findByCashierId(cashierId);
    }

    public List<Refund> getRefundsByBranch(Long branchId) {
        return refundRepository.findByBranchId(branchId);
    }

    public List<Refund> getRefundsByCashierAndDateRange(Long cashierId, LocalDateTime startDate, LocalDateTime endDate) {
        return refundRepository.findByCashierIdAndDateRange(cashierId, startDate, endDate);
    }

    public void deleteRefund(Long id) {
        refundRepository.deleteById(id);
    }
}
