package com.ETL.ETL;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EtlApplication {

	public static void main(String[] args) {
		SpringApplication.run(EtlApplication.class, args);
	}

	// INSTALLED KAFKA LOCALLY

	// brew install zookeeper
	// brew services start zookeeper
	// brew services stop zookeeper
	// START ZOOKEEPER BEFORE STARTING KAFKA
	// ZOOKEEPER RUNS ON PORT 2181

	// brew install kafka
	// brew services start kafka
	// brew services stop kafka
	// STOP KAFKA BEFORE STOPPING ZOOKEEPER
	// KAFKA RUNS ON PORT 9092

	// Configure Kafka for Development
	// You may want to configure the server.properties and other Kafka settings. The configuration file can be found at:
	// bash
	// /usr/local/etc/kafka/server.properties


}
