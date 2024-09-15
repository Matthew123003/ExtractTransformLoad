package com.ETL.ETL.Extract;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.ETL.ETL.Load.Load;
import com.ETL.ETL.Transform.Transform;

public class Extract {

    private Transform transform;

    public Extract() {
        this.transform = new Transform();  // Initialize the Transform class
    }

    // Extract data from a database and pass it to the transform step
    public void extractDataFromDatabase() {
        String jdbcUrl = "jdbc:mysql://localhost:3306/ff7";
        String username = "root";
        String password = "password";
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            // Connect to the database
            connection = DriverManager.getConnection(jdbcUrl, username, password);
            statement = connection.createStatement();

            // Execute a query to extract data
            String query = "SELECT id, first_name, last_name FROM ff7_characters";
            resultSet = statement.executeQuery(query);

            // Process the result set and send data to the transform class
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");

                // Send the extracted data to the transform step
                transform.transformData(id, firstName, lastName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close resources
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

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

