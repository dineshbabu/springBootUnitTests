package com.example.SanTest.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@ComponentScan(basePackages = "com.example.SanTest")
public class SanConfiguration {

	@Autowired
	Environment environment;

	@Bean
	public EmbeddedServletContainerCustomizer embeddedServletContainerCustomizer(){
		return (container -> {
			container.setPort(environment.getProperty("santest.port", Integer.class));
			container.setContextPath(environment.getProperty("santest.contextPath"));
		});
	}

	@Bean
	public Docket api(){
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.example.SanTest.controller"))
				.paths(PathSelectors.any())
				.build();
	}

	@Bean
	CommandLineRunner swaggerUrl(){
		return (String... args) ->{
			Logger logger = LoggerFactory.getLogger(SanConfiguration.class);
			logger.info("Swagger location : http://localhost:8080/santest/swagger-ui.html");
		};
	}
}
