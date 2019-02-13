package com.example.boot.reactive.reactiveSpringBootDemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ReactiveSpringBootDemoApplication {

	public static void main(String[] args) {

		SpringApplication.run(ReactiveSpringBootDemoApplication.class, args);
		ReactiveWebClient reactiveWebClient =new ReactiveWebClient();
		System.out.println(reactiveWebClient.getResult());
	}

}

