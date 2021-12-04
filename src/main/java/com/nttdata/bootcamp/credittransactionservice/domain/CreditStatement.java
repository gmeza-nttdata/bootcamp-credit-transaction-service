package com.nttdata.bootcamp.credittransactionservice.domain;

import com.nttdata.bootcamp.credittransactionservice.domain.dto.OperationType;
import com.nttdata.bootcamp.credittransactionservice.domain.entity.Credit;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class CreditStatement {
    private String id;
    private String productType;
    private String number;
    private OperationType operation;
    private BigDecimal amount;
    private LocalDateTime dateTime;
    private BigDecimal fee;

    public CreditStatement(){}


    public CreditStatement(Credit credit, OperationType operation, BigDecimal amount) {
        this.number = credit.getNumber();
        this.dateTime = LocalDateTime.now();
        this.operation = operation;
        this.amount = amount;
    }

}
