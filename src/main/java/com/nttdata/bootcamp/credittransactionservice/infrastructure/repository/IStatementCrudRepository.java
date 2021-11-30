package com.nttdata.bootcamp.credittransactionservice.infrastructure.repository;

import com.nttdata.bootcamp.credittransactionservice.infrastructure.model.dao.CreditStatementDao;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface IStatementCrudRepository extends ReactiveCrudRepository<CreditStatementDao, String> {
}
