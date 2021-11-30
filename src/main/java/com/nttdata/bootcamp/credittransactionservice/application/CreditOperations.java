package com.nttdata.bootcamp.credittransactionservice.application;

/*
 * Un cliente puede hacer pagos de sus productos de crédito.
 *
 * Un cliente puede cargar consumos a sus tarjetas de crédito en base a su límite de crédito.
 *
 * El sistema debe permitir consultar los saldos disponibles de tarjetas de crédito.
 *
 * El sistema debe permitir consultar todos los movimientos de un producto bancario que tiene un cliente.
 * */

import com.nttdata.bootcamp.credittransactionservice.domain.CreditStatement;
import com.nttdata.bootcamp.credittransactionservice.domain.dto.OperationData;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CreditOperations {

    Mono<CreditStatement> payCredit(OperationData operationData);
    Mono<CreditStatement> consumeCredit(OperationData operationData);
    Flux<CreditStatement> getStatements(String number);

}
