package com.nttdata.bootcamp.credittransactionservice.infrastructure.model.dao;

import com.nttdata.bootcamp.credittransactionservice.domain.dto.OperationType;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Document("CreditStatement")
public class CreditStatementDao {

    private String id;
    private String productType;
    private String number;
    private OperationType operation;
    private BigDecimal amount;
    private LocalDateTime dateTime;
    private BigDecimal fee;
}
