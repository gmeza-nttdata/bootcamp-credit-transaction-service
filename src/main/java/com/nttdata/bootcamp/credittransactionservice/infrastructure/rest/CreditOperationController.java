package com.nttdata.bootcamp.credittransactionservice.infrastructure.rest;

import com.nttdata.bootcamp.credittransactionservice.application.CreditOperations;
import com.nttdata.bootcamp.credittransactionservice.domain.CreditStatement;
import com.nttdata.bootcamp.credittransactionservice.domain.dto.OperationData;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

@RestController
@RequestMapping("/credit-transactions")
@RequiredArgsConstructor
public class CreditOperationController {
    private final CreditOperations creditOperations;

    @PostMapping("pay")
    Mono<CreditStatement> payCredit(@RequestBody OperationData operationData) {
        if (operationData.getAmount().compareTo(BigDecimal.ZERO) <= 0)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad Amount");
        return creditOperations.payCredit(operationData);
    }

    @PostMapping("consume")
    Mono<CreditStatement> consumeCredit(@RequestBody OperationData operationData){
        if (operationData.getAmount().compareTo(BigDecimal.ZERO) <= 0)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad Amount");
        return creditOperations.consumeCredit(operationData);
    }

    @GetMapping("statements/{number}")
    Flux<CreditStatement> getStatements(@PathVariable String number) {
        return creditOperations.getStatements(number);
    }

}
