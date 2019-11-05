package com.tokisaki.superadmin.security.jwt;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

@Configuration
@ConfigurationProperties(prefix = "jwt")
@Getter
@Setter
public class JwtProperties {
	@Value("${jwt.secretKey}")
	private String secretKey ;

	@Value("${jwt.validityInMs}")
	private long validityInMs ; 
}
