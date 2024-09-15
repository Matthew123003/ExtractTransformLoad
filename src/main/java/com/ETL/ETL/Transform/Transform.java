package com.ETL.ETL.Transform;

import java.util.Properties;
import org.apache.kafka.clients.producer.KafkaProducer;

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

    

}
