package com.jeiker.ssoclient2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;

@SpringBootApplication
@EnableOAuth2Sso
public class SsoClient2Application {

	public static void main(String[] args) {
		SpringApplication.run(SsoClient2Application.class, args);
	}
}
