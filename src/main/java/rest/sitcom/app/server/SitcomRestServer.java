/**
 * Copyright NEWFOUND SYSTEMS to Present
 * All Rights Reserved
 */
/**
 * 
 */
package rest.sitcom.app.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableAutoConfiguration(exclude = { org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration.class })
@ComponentScan(basePackages = { "rest.sitcom.app" })
@EntityScan(basePackages = { "rest.sitcom.app" })
@EnableJpaRepositories({ "rest.sitcom.app" })
public class SitcomRestServer {
	// http://www.springboottutorial.com/spring-boot-and-spring-jdbc-with-h2
	public static void main(String[] args) {
		System.out.println("Starting SpringBoot Simple Sitcom RESTful Server...");
		SpringApplication.run(SitcomRestServer.class, args);
	}
}