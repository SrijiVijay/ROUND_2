package com.kgisl.swaggerconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.kgisl.utils.ZuulConstant;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger class for API documentation.
 *
 * @author sriji
 * @version 1.0
 * @since Jan, 05 2021
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

	/**
	 * Method for Swagger Authentication.
	 * 
	 * @return Docket
	 */
	@Bean
	public Docket authenticationApi() {
		return new Docket(DocumentationType.SWAGGER_2)
		.select()
		.apis(RequestHandlerSelectors.any())
		.paths(PathSelectors.regex(ZuulConstant.SWAGGER_CONFIG_REGEX))
		.build();
	}
}
