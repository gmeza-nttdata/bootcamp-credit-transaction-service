package com.nttdata.bootcamp.credittransactionservice.application.service;

import com.nttdata.bootcamp.credittransactionservice.domain.entity.User;
import org.springframework.stereotype.Component;

@Component
public interface UserService extends IService<User,Integer> {
}
