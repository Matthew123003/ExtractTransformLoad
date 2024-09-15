package com.ETL.ETL.Load;

import java.util.Properties;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;


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

    // Load the transformed data to Kafka
    public void loadDataToKafka(int id, String transformedData) {
        // Create a producer record to send data to the Kafka topic
        String record = String.format("{\"id\": %d, \"name\": \"%s\"}", id, transformedData);
        
        // Send the transformed data to the Kafka topic
        producer.send(new ProducerRecord<>(TOPIC, Integer.toString(id), record));

        System.out.println("Loaded transformed data to Kafka: " + record);
    }

    // Close the Kafka producer when done
    public void close() {
        producer.close();
    }

}
