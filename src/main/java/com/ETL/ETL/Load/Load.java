package com.ETL.ETL.Load;

import java.util.Properties;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.stereotype.Component;


@Component
public class Load {

    private final KafkaProducer<String, String> producer;

    public Load() {
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        this.producer = new KafkaProducer<>(props);
    }

    // Load the transformed data to Kafka
    public void loadDataToKafka(int id, String transformedData) {
        String record = String.format("{\"id\": %d, \"name\": \"%s\"}", id, transformedData);
        producer.send(new ProducerRecord<>("ff7_topic", Integer.toString(id), record));
        System.out.println("Loaded transformed data to Kafka: " + record);
    }

    // Close the Kafka producer when done
    public void close() {
        producer.close();
    }

}
