/**
 * Copyright NEWFOUND SYSTEMS to Present
 * All Rights Reserved
 */
package rest.sitcom.app.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import rest.sitcom.app.interceptors.LogInterceptor;

@Configuration
@EnableWebSecurity
public class InterceptorConfig extends WebMvcConfigurerAdapter {

	private static final Logger logger = LogManager.getLogger(InterceptorConfig.class);
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		logger.info("---Adding interceptor...");
		registry.addInterceptor(new LogInterceptor()).addPathPatterns("/**");
	}
}