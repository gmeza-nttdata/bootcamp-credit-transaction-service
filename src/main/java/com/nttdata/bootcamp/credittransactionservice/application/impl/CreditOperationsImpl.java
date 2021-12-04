package com.nttdata.bootcamp.credittransactionservice.application.impl;

import com.nttdata.bootcamp.credittransactionservice.application.CreditOperations;
import com.nttdata.bootcamp.credittransactionservice.application.repository.StatementRepository;
import com.nttdata.bootcamp.credittransactionservice.domain.CreditStatement;
import com.nttdata.bootcamp.credittransactionservice.domain.dto.OperationData;
import com.nttdata.bootcamp.credittransactionservice.domain.dto.OperationType;
import com.nttdata.bootcamp.credittransactionservice.infrastructure.service.CreditWebService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class CreditOperationsImpl implements CreditOperations {
    private final StatementRepository statementRepository;
    private final CreditWebService creditWebService;

 
    @Override
    public Mono<CreditStatement> payCredit(OperationData operationData) {
        return creditWebService.get(operationData.getNumber())
                .flatMap(credit -> {
                    // Payment Operation
                    credit.setBalance(credit.getBalance().subtract(operationData.getAmount()));
                    return creditWebService.update(credit.getNumber(), credit)
                            .flatMap(
                                    updatedCredit ->
                                            statementRepository
                                                    .create(new CreditStatement(updatedCredit, OperationType.PAYMENT, operationData.getAmount()))
                            )
                            .onErrorReturn(new CreditStatement());
                })
                .onErrorReturn(new CreditStatement());
    }

    @Override
    public Mono<CreditStatement> consumeCredit(OperationData operationData) {
        return creditWebService.get(operationData.getNumber())
                .flatMap(credit -> {
                    if (credit.getBalance().add(operationData.getAmount()).compareTo(credit.getCreditLine()) > 0)
                        return null;
                    // Consumption Operation
                    credit.setBalance(credit.getBalance().add(operationData.getAmount()));
                    return creditWebService.update(credit.getNumber(), credit)
                            .flatMap(
                                    updatedCredit ->
                                            statementRepository
                                                    .create(new CreditStatement(updatedCredit, OperationType.CONSUMPTION, operationData.getAmount()))
                            )
                            .onErrorReturn(new CreditStatement());
                })
                .onErrorReturn(new CreditStatement());
    }


    @Override
    public Flux<CreditStatement> getStatements(String number) {
        return statementRepository.queryAll()
                .filter(statement -> statement.getNumber().equals(number)
                    );
    }

    @Override
    public Flux<CreditStatement> getAllStatements() {
        return statementRepository.queryAll();
    }
}
