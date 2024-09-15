package com.ETL.ETL;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ETL.ETL.Extract.Extract;

@SpringBootApplication
public class EtlApplication {

	public static void main(String[] args) {
		SpringApplication.run(EtlApplication.class, args);
		Extract extractor = new Extract();
        extractor.extractDataFromDatabase();
	}

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
// Kafka Environment Configured

// START ZOOKEEPER
// zookeeper-server-start.sh /path/to/kafka/config/zookeeper.properties

// START KAFKA
// kafka-server-start.sh /path/to/kafka/config/server.properties

// CREATE THE TOPIC
// kafka-topics.sh --create --topic ff7_topic --bootstrap-server localhost:9092 --partitions 1 --replication-factor 1

// VERFIY TOPIC CREATION
// kafka-topics.sh --list --bootstrap-server localhost:9092

// DESCRIBE TOPIC
// kafka-topics.sh --describe --topic ff7_topic --bootstrap-server localhost:9092

// ADJUST TOPIC PARTITIONS
// kafka-topics.sh --alter --topic ff7_topic --partitions <new_partition_count> --bootstrap-server localhost:9092

// ADJUST TOPIC REPLICATION FACTOR
// Generate Assignment Plan
// kafka-reassign-partitions.sh --generate --topics-to-move-json-file topics.json --broker-list <broker_ids> --bootstrap-server localhost:9092
// Execute Assignment Plan
// kafka-reassign-partitions.sh --execute --reassignment-json-file reassignment.json --bootstrap-server localhost:9092

// ADJUST TOPIC CONFIGURATIONS
// kafka-configs.sh --alter --entity-type topics --entity-name ff7_topic --add-config <config_key>=<value> --bootstrap-server localhost:9092

// ADJUST TOPIC SIZE TO 512MB
// kafka-configs.sh --alter --entity-type topics --entity-name ff7_topic --add-config segment.bytes=536870912 --bootstrap-server localhost:9092

// ADJUST RETENTION TIME OF TOPIC
// kafka-configs.sh --alter --entity-type topics --entity-name ff7_topic --add-config retention.ms=<retention_time_in_ms> --bootstrap-server localhost:9092

// ADJUST RETENTION TIME TO 2 DAYS 72800000ms
// kafka-configs.sh --alter --entity-type topics --entity-name ff7_topic --add-config retention.ms=172800000 --bootstrap-server localhost:9092

// ADJUST CLEANUP POLICY
// kafka-configs.sh --alter --entity-type topics --entity-name ff7_topic --add-config cleanup.policy=<policy> --bootstrap-server localhost:9092

// ENABLE LOG COMPACTION
// kafka-configs.sh --alter --entity-type topics --entity-name ff7_topic --add-config cleanup.policy=compact --bootstrap-server localhost:9092

// SET TO DELETE OLD LOGS AFTER RETENTION TIME
// kafka-configs.sh --alter --entity-type topics --entity-name ff7_topic --add-config cleanup.policy=delete --bootstrap-server localhost:9092

// ADJUST MESSAGE SIZE
// kafka-configs.sh --alter --entity-type topics --entity-name ff7_topic --add-config max.message.bytes=<size_in_bytes> --bootstrap-server localhost:9092

// ADJUST MESSAGE SIZE TO 2MB
// kafka-configs.sh --alter --entity-type topics --entity-name ff7_topic --add-config max.message.bytes=2097152 --bootstrap-server localhost:9092

// ADJUST SEGMENT SIZE
// kafka-configs.sh --alter --entity-type topics --entity-name ff7_topic --add-config segment.bytes=<size_in_bytes> --bootstrap-server localhost:9092

// ADJUST SEGMENT SIZE TO 512MB
// kafka-configs.sh --alter --entity-type topics --entity-name ff7_topic --add-config segment.bytes=536870912 --bootstrap-server localhost:9092

// ADJUST RETENTION SIZE
// kafka-configs.sh --alter --entity-type topics --entity-name ff7_topic --add-config retention.bytes=<size_in_bytes> --bootstrap-server localhost:9092

// LIMIT RETENTION TO 10GB
// kafka-configs.sh --alter --entity-type topics --entity-name ff7_topic --add-config retention.bytes=10737418240 --bootstrap-server localhost:9092
