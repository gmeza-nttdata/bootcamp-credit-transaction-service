package com.nttdata.bootcamp.credittransactionservice.application.service;

import com.nttdata.bootcamp.credittransactionservice.domain.entity.Credit;
import org.springframework.stereotype.Component;

@Component
public interface CreditService extends IService<Credit,String> {
}
