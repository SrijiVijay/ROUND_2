package com.kgisl.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

/**
 * The Class PremiumGeneratorApiApplication.
 */
@SpringBootApplication(scanBasePackages = { "com.kgisl" })
@EnableDiscoveryClient
@ComponentScan(basePackages = "com.kgisl ")
@RefreshScope
public class PremiumGeneratorApiApplication  {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(PremiumGeneratorApiApplication.class, args);
	}
	
	/**
	 * Adds the view controllers.
	 *
	 * @param registry the registry
	 */
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/Premium-Generator/home").setViewName("findPremium");;
	}
}
