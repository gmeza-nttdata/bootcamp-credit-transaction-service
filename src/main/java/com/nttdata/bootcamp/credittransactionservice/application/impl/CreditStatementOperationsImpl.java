package com.nttdata.bootcamp.credittransactionservice.application.impl;

import com.nttdata.bootcamp.credittransactionservice.application.CreditStatementOperations;
import com.nttdata.bootcamp.credittransactionservice.application.repository.StatementRepository;
import com.nttdata.bootcamp.credittransactionservice.domain.CreditStatement;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CreditStatementOperationsImpl implements CreditStatementOperations {

    private final StatementRepository statementRepository;

    @Override
    public Flux<CreditStatement> queryAll() {
        return statementRepository.queryAll();
    }

    @Override
    public Mono<CreditStatement> findById(String id) {
        return statementRepository.findById(id);
    }

    @Override
    public Mono<CreditStatement> create(CreditStatement statement) {
        return statementRepository.create(statement);
    }

    @Override
    public Mono<CreditStatement> update(String id, CreditStatement statement) {
        return statementRepository.update(id, statement);
    }

    @Override
    public Mono<Void> delete(String id) {
        return statementRepository.delete(id);
    }
}
