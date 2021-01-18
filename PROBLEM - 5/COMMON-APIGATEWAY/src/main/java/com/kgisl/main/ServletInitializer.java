package com.kgisl.main;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * ServletInitializer class to run SpringApplication from a traditional WAR
 * deployment.
 *
 * @author sriji.m
 */
public class ServletInitializer extends SpringBootServletInitializer {

	/**
	 * Configure.
	 *
	 * @param application the application
	 * @return the spring application builder
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.boot.web.support.SpringBootServletInitializer#configure(
	 * org.springframework.boot.builder.SpringApplicationBuilder)
	 */
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(ZuulgatewayproxyApplication.class);
	}

}