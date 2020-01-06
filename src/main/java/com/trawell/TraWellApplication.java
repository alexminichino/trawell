package com.trawell;

import com.trawell.storage.configuration.FileStorageProperties;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties({ FileStorageProperties.class })
@SpringBootApplication
public class TraWellApplication {

	public static void main(String[] args) {
		SpringApplication.run(TraWellApplication.class, args);
	}

}
