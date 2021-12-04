package com.nttdata.bootcamp.credittransactionservice.domain.entity;

import com.nttdata.bootcamp.credittransactionservice.domain.dto.CreditType;
import com.nttdata.bootcamp.credittransactionservice.domain.dto.Type;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Slf4j
@Data
public class Credit {

    private String number;
    private Integer userId;
    private Boolean hasCard;
    private String currencyName;
    private BigDecimal balance;

    private BigDecimal creditLine;
    private BigDecimal rate;
    private Integer cutoffDate;
    private Integer paymentDate;

}
