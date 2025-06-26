package com.example.financial_transactions.repository;

import com.example.financial_transactions.model.FinancialTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FinancialTransactionRepository extends JpaRepository<FinancialTransaction, Long> {
    Page<FinancialTransaction> findByDateBetweenAndUserIdAndServiceAndStatusAndReference(
            String dateFrom, String dateTo, String userId, String service, String status, String reference,
            Pageable pageable);
}
