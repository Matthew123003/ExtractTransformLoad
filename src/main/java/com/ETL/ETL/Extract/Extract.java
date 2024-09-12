package com.ETL.ETL.Extract;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
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

    // Extract data from a database and send to Kafka
    public void extractDataFromDatabase() {
        String jdbcUrl = "jdbc:mysql://localhost:3306/ff7";
        String username = "root";
        String password = "password";
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        

    };

}
