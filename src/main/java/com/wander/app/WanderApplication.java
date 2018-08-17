package com.wander.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * 
 * @author mrsagar
 *
 */
@Configuration
@ComponentScan(basePackages = "com.wander")
@EnableAutoConfiguration
@EnableJpaRepositories(basePackages = "com.wander")
@EntityScan(basePackages = "com.wander")
public class WanderApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(WanderApplication.class);
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(WanderApplication.class, args);
	}
}