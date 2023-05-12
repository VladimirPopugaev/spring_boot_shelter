package com.popugaevvn.spring_boot_shelter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootShelterApplication {

	private static final Logger LOGGER = LogManager.getLogger(SpringBootShelterApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SpringBootShelterApplication.class, args);
		LOGGER.debug("Debug message");
		LOGGER.info("info message");
		LOGGER.warn("warn message");
		LOGGER.error("Error message");
		LOGGER.fatal("Fatal message");
	}

}
