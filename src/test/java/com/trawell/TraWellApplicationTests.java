package com.trawell;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootTest
@SpringBootApplication
@ComponentScan(basePackages="com.trawell")
@EntityScan(basePackages="com.trawell")
@EnableJpaRepositories(basePackages="com.trawell")

class TraWellApplicationTests {

	@Test
	void contextLoads() {
	}

}
