package com.kgisl.main;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Centralized configuration class to place all external properties for
 * applications across all microservices against different environment.
 *
 * @author Sriji.n
 * @version 1.0
 * @since Jan, 05 2021
 */
@SpringBootApplication(scanBasePackages = {"com.kgisl"} , exclude = { ManagementWebSecurityAutoConfiguration.class,
		SecurityAutoConfiguration.class })
@EnableDiscoveryClient
@EnableConfigServer
@RestController
public class ConfigServerApplication {

	/** The instance name. */
	@Value("${service.Instance.name}")
	private String instanceName;

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(ConfigServerApplication.class, args);
	}

	/**
	 * Instance.
	 *
	 * @return the string
	 */
	@RequestMapping("/")
	public String instance() {
		return "hello - " + instanceName;
	}
}