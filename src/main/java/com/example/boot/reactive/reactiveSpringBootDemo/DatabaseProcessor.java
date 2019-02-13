package com.example.boot.reactive.reactiveSpringBootDemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Component
public class DatabaseProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(RequestProcessor.class);

    @Autowired
    AccountRespository accountRespository;

    public Mono<Account> getAccountbyAccountNumber(String AccountNumber){

        return accountRespository.getAccountByAccountNumber(AccountNumber).doOnSuccess(
                res -> LOGGER.info("RESPONSE::"+ "Account Name:: "+res.getAccountName()+
                        " Account Number:: "+res.getAccountNumber()+
                        " Account Status:: "+res.getAccountStatus()+
                        " Account Type:: "+res.getAccountType()+
                        " Bill Type:: "+res.getBillType())
        ).doOnError(exp -> {
            LOGGER.info("Exception encountered in getAccountbyAccountNumber:: "+ exp.getMessage());
        }).onErrorReturn(new Account());
    }

    public Flux<Account> getAccountbyAccountName(String AccountName){

        return accountRespository.getAccountByAccountName(AccountName).
        doOnError(exp -> {
            LOGGER.info("Exception encountered in getAccountbyAccountName :: "+ exp.getMessage());
        }).onErrorReturn(new Account());
    }

}
