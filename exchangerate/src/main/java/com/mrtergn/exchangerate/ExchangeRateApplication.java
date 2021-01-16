package com.mrtergn.exchangerate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
public class ExchangeRateApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExchangeRateApplication.class, args);
	}

	@Bean(name = "loadBalancedRestTemplate")
	@LoadBalanced
	public RestTemplate getLoadBalancedRestTemplate(){
		return new RestTemplate();
	}

	@Bean(name = "restTemplate")
	public RestTemplate getRestTemplate(){
		return new RestTemplate();
	}

}
