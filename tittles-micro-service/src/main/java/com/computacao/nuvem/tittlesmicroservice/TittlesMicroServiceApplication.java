package com.computacao.nuvem.tittlesmicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@SpringBootApplication
public class TittlesMicroServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TittlesMicroServiceApplication.class, args);
	}

}
