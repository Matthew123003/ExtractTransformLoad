package com.ETL.ETL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseAutoConfiguration;
import org.springframework.boot.autoconfigure.r2dbc.R2dbcAutoConfiguration;
import org.springframework.kafka.annotation.EnableKafka;
import com.ETL.ETL.Extract.Extract;


@SpringBootApplication(exclude = {R2dbcAutoConfiguration.class, LiquibaseAutoConfiguration.class})
@EnableKafka
public class EtlApplication implements CommandLineRunner{

	@Autowired
	private final Extract extractor;

    public EtlApplication(Extract extractor) {
        this.extractor = extractor;  // Inject the Extract bean
    }

    public static void main(String[] args) {
        SpringApplication.run(EtlApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // Start the ETL process after the application starts
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
// brew services start zookeeper
// brew services stop zookeeper

// START KAFKA
// kafka-server-start.sh /path/to/kafka/config/server.properties
// brew services start kafka
// brew services stop kafka

// SHOW KAFKA PATH LOCATIONS
// brew list kafka

// CREATE THE TOPIC
// /opt/homebrew/Cellar/kafka/3.8.0/bin/kafka-topics --create --topic <TOPIC NAME> --bootstrap-server localhost:9092 --partitions 1 --replication-factor 1


// VERFIY TOPIC CREATION
// /opt/homebrew/Cellar/kafka/3.8.0/bin/kafka-topics --list --bootstrap-server localhost:9092

// DESCRIBE TOPIC
// /opt/homebrew/Cellar/kafka/3.8.0/bin/kafka-topics --describe --topic ff7_topic --bootstrap-server localhost:9092

// ADJUST TOPIC PARTITIONS
// /opt/homebrew/Cellar/kafka/3.8.0/bin/kafka-topics --alter --topic ff7_topic --partitions <new_partition_count> --bootstrap-server localhost:9092

// ADJUST TOPIC REPLICATION FACTOR
// Generate Assignment Plan
// /opt/homebrew/Cellar/kafka/3.8.0/bin/kafka-reassign-partitions --generate --topics-to-move-json-file topics.json --broker-list <broker_ids> --bootstrap-server localhost:9092
// Execute Assignment Plan
// /opt/homebrew/Cellar/kafka/3.8.0/bin/kafka-reassign-partitions --execute --reassignment-json-file reassignment.json --bootstrap-server localhost:9092

// ADJUST TOPIC CONFIGURATIONS
// /opt/homebrew/Cellar/kafka/3.8.0/bin/kafka-configs --alter --entity-type topics --entity-name ff7_topic --add-config <config_key>=<value> --bootstrap-server localhost:9092

// ADJUST TOPIC SIZE TO 512MB
// /opt/homebrew/Cellar/kafka/3.8.0/bin/kafka-configs --alter --entity-type topics --entity-name ff7_topic --add-config segment.bytes=536870912 --bootstrap-server localhost:9092

// ADJUST RETENTION TIME OF TOPIC
// /opt/homebrew/Cellar/kafka/3.8.0/bin/kafka-configs --alter --entity-type topics --entity-name ff7_topic --add-config retention.ms=<retention_time_in_ms> --bootstrap-server localhost:9092

// ADJUST RETENTION TIME TO 2 DAYS 72800000ms
// /opt/homebrew/Cellar/kafka/3.8.0/bin/kafka-configs --alter --entity-type topics --entity-name ff7_topic --add-config retention.ms=172800000 --bootstrap-server localhost:9092

// ADJUST CLEANUP POLICY
// /opt/homebrew/Cellar/kafka/3.8.0/bin/kafka-configs --alter --entity-type topics --entity-name ff7_topic --add-config cleanup.policy=<policy> --bootstrap-server localhost:9092

// ENABLE LOG COMPACTION
// /opt/homebrew/Cellar/kafka/3.8.0/bin/kafka-configs --alter --entity-type topics --entity-name ff7_topic --add-config cleanup.policy=compact --bootstrap-server localhost:9092

// SET TO DELETE OLD LOGS AFTER RETENTION TIME
// /opt/homebrew/Cellar/kafka/3.8.0/bin/kafka-configs --alter --entity-type topics --entity-name ff7_topic --add-config cleanup.policy=delete --bootstrap-server localhost:9092

// ADJUST MESSAGE SIZE
// /opt/homebrew/Cellar/kafka/3.8.0/bin/kafka-configs --alter --entity-type topics --entity-name ff7_topic --add-config max.message.bytes=<size_in_bytes> --bootstrap-server localhost:9092

// ADJUST MESSAGE SIZE TO 2MB
// /opt/homebrew/Cellar/kafka/3.8.0/bin/kafka-configs --alter --entity-type topics --entity-name ff7_topic --add-config max.message.bytes=2097152 --bootstrap-server localhost:9092

// ADJUST SEGMENT SIZE
// /opt/homebrew/Cellar/kafka/3.8.0/bin/kafka-configs --alter --entity-type topics --entity-name ff7_topic --add-config segment.bytes=<size_in_bytes> --bootstrap-server localhost:9092

// ADJUST SEGMENT SIZE TO 512MB
// /opt/homebrew/Cellar/kafka/3.8.0/bin/kafka-configs --alter --entity-type topics --entity-name ff7_topic --add-config segment.bytes=536870912 --bootstrap-server localhost:9092

// ADJUST RETENTION SIZE
// /opt/homebrew/Cellar/kafka/3.8.0/bin/kafka-configs --alter --entity-type topics --entity-name ff7_topic --add-config retention.bytes=<size_in_bytes> --bootstrap-server localhost:9092

// LIMIT RETENTION TO 10GB
// /opt/homebrew/Cellar/kafka/3.8.0/bin/kafka-configs --alter --entity-type topics --entity-name ff7_topic --add-config retention.bytes=10737418240 --bootstrap-server localhost:9092

//FIGURE OUT WHAT IS RUNNING ON A CERTAIN PORT
// lsof -i :<PORT NUMBER>

// CHECK STATUS OF WHAT IS RUNNING ON A CERTAIN PORT
// ps -p 51588(PID) -o comm,args

// CHECK ZOOKEEPER STATUS
// jps | grep QuorumPeerMain

// CHECK KAFKA STATUS
// jps | grep Kafka


