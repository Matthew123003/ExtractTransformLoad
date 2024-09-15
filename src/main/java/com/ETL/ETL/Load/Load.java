package com.ETL.ETL.Load;

import java.util.Properties;
import org.apache.kafka.clients.producer.KafkaProducer;

public class Load {

    private static final String TOPIC = "ff7_topic";  // Kafka topic to load the data
    private KafkaProducer<String, String> producer;

    public Load() {
        // Set up Kafka producer properties
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");  // Kafka broker
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        // Initialize Kafka producer
        this.producer = new KafkaProducer<>(props);
    }
    
    

}
