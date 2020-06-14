package com.code.facturacion.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ApplicationProperties {
	
	@Bean
	@ConfigurationProperties("application.cdn")
	public CDN getCDN() {
		return new CDN();
	}
	
	
	public class CDN {
		private String baseFolder;

		public String getBaseFolder() {
			return baseFolder;
		}

		public void setBaseFolder(String baseFolder) {
			this.baseFolder = baseFolder;
		}
	}
	
	
	@Bean
	@ConfigurationProperties("application.jwt")
	public jwt getJWT() {
		return new jwt();
	}
	
	
	public class jwt {
		private String base64Secret ;
		private long expirationInSeconds ;
		

		public String getBase64Secret() {
			return base64Secret;
		}

		public void setBase64Secret(String base64Secret) {
			this.base64Secret = base64Secret;
		}

		public long getExpirationInSeconds() {
			return expirationInSeconds;
		}

		public void setExpirationInSeconds(long expirationInSeconds) {
			this.expirationInSeconds = expirationInSeconds;
		}
		
		
	}
	
	
}
