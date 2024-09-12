package com.ETL.ETL.Extract;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
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

        try{
            // Connect to the database
            connection = DriverManager.getConnection(jdbcUrl, username, password);
            statement = connection.createStatement();

            // Execute a query to extract data
            String query = "SELECT id, name, value FROM ff7_characters";
            resultSet = statement.executeQuery(query);
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            // Close resources
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    };

}
