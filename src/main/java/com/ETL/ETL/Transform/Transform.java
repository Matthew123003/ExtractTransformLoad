package com.ETL.ETL.Transform;

import org.springframework.stereotype.Component;
import com.ETL.ETL.Load.Load;


@Component
public class Transform {
    
    private final Load load;

    public Transform(Load load) {
        this.load = load;  // Inject Load via constructor
    }

    // Transform the data and pass it to the Load class
    public void transformData(int id, String firstName, String lastName) {
        String transformedData = (firstName + " " + lastName).toUpperCase();
        load.loadDataToKafka(id, transformedData);
    }
}
