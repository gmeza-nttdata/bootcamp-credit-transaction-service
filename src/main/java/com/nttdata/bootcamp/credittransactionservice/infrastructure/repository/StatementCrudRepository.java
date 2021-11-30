package com.nttdata.bootcamp.credittransactionservice.infrastructure.repository;

import com.nttdata.bootcamp.credittransactionservice.application.repository.StatementRepository;
import com.nttdata.bootcamp.credittransactionservice.domain.CreditStatement;
import com.nttdata.bootcamp.credittransactionservice.infrastructure.model.dao.CreditStatementDao;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class StatementCrudRepository implements StatementRepository {

    @Autowired
    IStatementCrudRepository repository;


    @Override
    public Flux<CreditStatement> queryAll() {
        return repository.findAll()
                .map(this::mapStatementDaoToStatement);
    }

    @Override
    public Mono<CreditStatement> findById(String id) {
        return repository.findById(id)
                .map(this::mapStatementDaoToStatement);
    }

    @Override
    public Mono<CreditStatement> create(CreditStatement statement) {
        return repository.save(mapStatementToStatementDao(statement))
                .map(this::mapStatementDaoToStatement);
    }

    @Override
    public Mono<CreditStatement> update(String id, CreditStatement statement) {
        return repository.findById(id)
                .flatMap(s -> {
                    s.setId(id);
                    return Mono.just(mapStatementToStatementDao(statement));
                }).flatMap(repository::save)
                .map(this::mapStatementDaoToStatement);
    }

    @Override
    public Mono<Void> delete(String id) {
        return repository.deleteById(id);
    }

    private CreditStatement mapStatementDaoToStatement(CreditStatementDao creditStatementDao) {
        CreditStatement statement = new CreditStatement();
        BeanUtils.copyProperties(creditStatementDao, statement);
        return statement;
    }

    private CreditStatementDao mapStatementToStatementDao(CreditStatement statement) {
        CreditStatementDao creditStatementDao = new CreditStatementDao();
        BeanUtils.copyProperties(statement, creditStatementDao);
        return creditStatementDao;
    }


}
