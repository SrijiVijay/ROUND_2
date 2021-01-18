package com.project.paymentservice.service.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.ComponentScan;

/**
 * The Class PaymentServiceApplication.
 */
@ComponentScan(basePackages = "com.project.paymentservice")
@SpringBootApplication(scanBasePackages = { "com.project.paymentservice" })
@EnableDiscoveryClient
@RefreshScope
public class PaymentServiceApplication {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(PaymentServiceApplication.class, args);
	}
}