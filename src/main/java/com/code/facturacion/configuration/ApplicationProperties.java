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
	
	
}
