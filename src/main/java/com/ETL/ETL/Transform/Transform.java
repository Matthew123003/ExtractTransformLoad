package com.ETL.ETL.Transform;

import com.ETL.ETL.Load.Load;

public class Transform {
    private Load load;

    public Transform() {
        this.load = new Load();  // Initialize the Load class
    }

    // Transform the data and pass it to the Load class
    public void transformData(int id, String firstName, String lastName) {
        // Transformation logic: concatenate first and last names and convert to uppercase
        String transformedData = (firstName + " " + lastName).toUpperCase();

        // Send the transformed data to the Load class
        load.loadDataToKafka(id, transformedData);
    }
}
