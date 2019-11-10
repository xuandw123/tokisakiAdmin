package com.tokisaki.superadmin.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class WebMvcConfig  implements WebMvcConfigurer {
	  @Override
	    public void addCorsMappings(CorsRegistry registry) {
	        registry.addMapping("/**")
	                .allowedOrigins("*")
	                .allowedHeaders("*")
	                .exposedHeaders("location")
	                .allowCredentials(true)
	                .allowedMethods("GET", "POST", "DELETE", "PUT", "OPTIONS")
	                .maxAge(3600);
	    }
}