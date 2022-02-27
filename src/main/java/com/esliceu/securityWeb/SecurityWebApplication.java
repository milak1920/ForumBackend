package com.esliceu.securityWeb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity
public class SecurityWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecurityWebApplication.class, args);
	}

}
