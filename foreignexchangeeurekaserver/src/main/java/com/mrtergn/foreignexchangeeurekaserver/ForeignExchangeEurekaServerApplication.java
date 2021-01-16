package com.mrtergn.foreignexchangeeurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class ForeignExchangeEurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ForeignExchangeEurekaServerApplication.class, args);
	}

}
