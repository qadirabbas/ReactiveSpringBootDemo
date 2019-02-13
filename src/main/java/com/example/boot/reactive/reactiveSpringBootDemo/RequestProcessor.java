package com.example.boot.reactive.reactiveSpringBootDemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Component
public class RequestProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(RequestProcessor.class);


    @Autowired
    DatabaseProcessor databaseProcessor;


    public Mono<ServerResponse> hello(ServerRequest request){
        return ServerResponse.ok().contentType(MediaType.TEXT_PLAIN).body(BodyInserters.fromObject("Hello, Qadir !!"));
    }

    public Mono<ServerResponse> fetchAccountInfoFromAccountNumber(ServerRequest request){
        String AccountNumber=request.queryParam("accountnumber").get();
        LOGGER.info("Account Number in Request:: "+AccountNumber);
      Mono<Account> response = databaseProcessor.getAccountbyAccountNumber(AccountNumber);
                /*response.
                subscribe(res ->
                        LOGGER.info("RESPONSE::"+ "Account Name:: "+res.getAccountName()+
                                " Account Number:: "+res.getAccountNumber()+
                                " Account Status:: "+res.getAccountStatus()+
                                " Account Type:: "+res.getAccountType()+
                                " Bill Type:: "+res.getBillType())); */
        return ServerResponse.ok().body(response,Account.class);
    }

    public Mono<ServerResponse> fetchAccountInfoFromAccountName(ServerRequest request){
        String AccountName=request.queryParam("accountname").get();
        LOGGER.info("Account Number in Request:: "+AccountName);
        Flux<Account> response = databaseProcessor.getAccountbyAccountName(AccountName);
                /*response.
                subscribe(res ->
                        LOGGER.info("RESPONSE::"+ "Account Name:: "+res.getAccountName()+
                                " Account Number:: "+res.getAccountNumber()+
                                " Account Status:: "+res.getAccountStatus()+
                                " Account Type:: "+res.getAccountType()+
                                " Bill Type:: "+res.getBillType())); */
        return ServerResponse.ok().body(response,Account.class);
    }
}
