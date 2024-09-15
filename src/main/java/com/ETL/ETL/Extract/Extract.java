package com.ETL.ETL.Extract;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.springframework.stereotype.Component;
import com.ETL.ETL.Transform.Transform;


@Component
public class Extract {

    private final Transform transform;

    public Extract(Transform transform) {
        this.transform = transform;  // Inject Transform via constructor
    }

    // Extract data from a database and pass it to the transform step
    public void extractDataFromDatabase() {
        String jdbcUrl = "jdbc:mysql://localhost:3306/ff7";
        String username = "UN";
        String password = "PW";
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection(jdbcUrl, username, password);
            statement = connection.createStatement();
            String query = "SELECT id, first_name, last_name FROM ff7_characters";
            resultSet = statement.executeQuery(query);

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

