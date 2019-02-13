package com.example.boot.reactive.reactiveSpringBootDemo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.server.*;

@Configuration
public class Router {

    @Bean
    public RouterFunction<ServerResponse> route(RequestProcessor requestProcessor){
        return RouterFunctions.route(RequestPredicates.GET("/hello")
                                    .and(RequestPredicates.accept(MediaType.TEXT_PLAIN)),requestProcessor::hello);
    }

    @Bean
    public RouterFunction<ServerResponse> accountRoutebyAccountNumber(RequestProcessor requestProcessor){
        return RouterFunctions.route(RequestPredicates.GET("/getaccountdetails/number")
                .and(RequestPredicates.accept(MediaType.TEXT_PLAIN)),requestProcessor::fetchAccountInfoFromAccountNumber);
    }

    @Bean
    public RouterFunction<ServerResponse> accountRouteByAccountName(RequestProcessor requestProcessor){
        return RouterFunctions.route(RequestPredicates.GET("/getaccountdetails/name")
                .and(RequestPredicates.accept(MediaType.TEXT_PLAIN)),requestProcessor::fetchAccountInfoFromAccountName);
    }
}
