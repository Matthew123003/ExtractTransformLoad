package com.ETL.ETL.Transform;

import java.util.Properties;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

public class Transform {
    private static final String TOPIC = "transformed_ff7_topic";
    private KafkaProducer<String, String> producer;

    public Transform() {
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");  // Kafka broker
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        this.producer = new KafkaProducer<>(props);
    }

    // This method transforms the first and last names into full name and makes them uppercase
    public String transformData(String firstName, String lastName) {
        String fullName = firstName + " " + lastName;
        return fullName.toUpperCase();  // Transformation logic: concatenating and converting to uppercase
    }

    // This method sends transformed data to Kafka
    public void sendTransformedDataToKafka(int id, String transformedData) {
        // Create the transformed record
        String record = String.format("{\"id\": %d, \"name\": \"%s\"}", id, transformedData);

        // Send the transformed data to Kafka
        producer.send(new ProducerRecord<>(TOPIC, Integer.toString(id), record));

        System.out.println("Sent transformed record to Kafka: " + record);
    }

    // Close the Kafka producer when done
    public void close() {
        producer.close();
    }

}
