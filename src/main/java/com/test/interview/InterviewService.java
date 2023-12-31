package com.test.interview;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebFlux;


//@EnableDiscoveryClient
@SpringBootApplication
@EnableTransactionManagement // to use @transactional
@EnableSwagger2WebFlux
public class InterviewService {

	public static void main(String[] args) {
		SpringApplication.run(InterviewService.class, args);
	}

}
