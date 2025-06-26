package com.example.financial_transactions.controller;

import com.example.financial_transactions.model.FinancialTransaction;
import com.example.financial_transactions.model.Payment;
import com.example.financial_transactions.model.DataListPaymentResponse;
import com.example.financial_transactions.repository.FinancialTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class FinancialTransactionController {

    @Autowired
    private FinancialTransactionRepository financialTransactionRepository;

    @GetMapping("/transactions")
    public Mono<ResponseEntity<DataListPaymentResponse>> fetchTransactions(
            @RequestParam String dateFrom,
            @RequestParam String dateTo,
            @RequestParam String userId,
            @RequestParam String service,
            @RequestParam int offset,
            @RequestParam int limit,
            @RequestParam String status,
            @RequestParam String reference) {

        Pageable pageable = PageRequest.of(offset, limit);

        return Mono
                .fromCallable(
                        () -> financialTransactionRepository.findByDateBetweenAndUserIdAndServiceAndStatusAndReference(
                                dateFrom, dateTo, userId, service, status, reference, pageable))
                .flatMap(page -> {
                    List<FinancialTransaction> transactions = page.getContent();
                    Flux<Payment> paymentFlux = Flux.fromIterable(transactions)
                            .flatMap(transaction -> retrieveFinancialTransaction(transaction.getPaymentId()));

                    return paymentFlux.collectList()
                            .map(payments -> {
                                List<Payment> sortedPayments = payments.stream()
                                        .sorted(Comparator.comparing(Payment::getId).reversed())
                                        .collect(Collectors.toList());
                                DataListPaymentResponse response = new DataListPaymentResponse();
                                response.setPayments(sortedPayments);
                                return ResponseEntity.ok(response);
                            });
                })
                .onErrorResume(WebClientResponseException.class, e -> {
                    DataListPaymentResponse errorResponse = new DataListPaymentResponse();
                    errorResponse.setPayments(List.of());
                    return Mono.just(ResponseEntity.status(e.getStatusCode()).body(errorResponse));
                });
    }

    private Mono<Payment> retrieveFinancialTransaction(String paymentId) {
        return Mono.just(new Payment(paymentId));
    }
}
