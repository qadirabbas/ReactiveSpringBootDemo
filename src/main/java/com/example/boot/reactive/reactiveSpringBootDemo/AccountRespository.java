package com.example.boot.reactive.reactiveSpringBootDemo;

import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AccountRespository extends ReactiveCassandraRepository<Account, String> {

    Mono<Account> getAccountByAccountNumber(String accountNumber);
    @AllowFiltering
    Flux<Account> getAccountByAccountName(String accountName);
}
