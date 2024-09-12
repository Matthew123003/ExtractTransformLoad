package com.ETL.ETL.Extract;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;

public class Extract {

    private static final String TOPIC = "etl_topic";
    private KafkaProducer<String, String> producer;

    public Extract(){
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");  // Kafka broker
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        this.producer = new KafkaProducer<>(props);
    };

    

}
