package com.nttdata.bootcamp.credittransactionservice.application.repository;

import com.nttdata.bootcamp.credittransactionservice.domain.CreditStatement;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface StatementRepository {
    Flux<CreditStatement> queryAll();
    Mono<CreditStatement> findById(String id);
    Mono<CreditStatement> create(CreditStatement statement);
    Mono<CreditStatement> update(String id, CreditStatement statement);
    Mono<Void> delete(String id);
}
