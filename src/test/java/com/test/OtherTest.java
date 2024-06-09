package com.test;

import com.cardio_generator.HealthDataSimulator;
import com.data_management.DataReader;
import com.data_management.DataStorage;
import com.data_management.PatientRecord;
import com.data_management.SomeDataReaderImplementation;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import static com.data_management.DataStorage.UP_Date;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class OtherTest {

    /**
     * Test whether the server starts up correctly.
     * @throws IOException
     */
    @Test
    public void testBeServer() throws IOException {
        HealthDataSimulator.main(new String[]{"-h","--patient-count","noNumber","12","--output","tcp:abc","--output","abc","--output","websocket:abc"});
    }

    /**
     * Test whether the client starts up correctly.
     */
    @Test
    public void testBeClient() throws IOException {
        testBeServer(); // Start the server first
        DataReader dataReader = new SomeDataReaderImplementation("src/main/java/bin/cc.txt/Saturation.txt");
        DataStorage storage = new DataStorage();

        // Assuming the reader has been properly initialized and can read data into the storage
        // reader.readData(storage);

        try {

            /**
             * Start a new thread to refresh client-side data input in real-time.
             */
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {

                    Set<PatientRecord> set = new HashSet<>(); // Use a set to remove duplicate data

                    while (true) {
                        try {
                            Thread.sleep(5000); // Refresh interval, 5 seconds
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        UP_Date(storage, set); // Refresh method
                    }
                }
            });

            thread.start();
            dataReader.readData(storage);
            Thread.sleep(5000);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertTrue(storage.getAllPatients().size() > 0); // Test if the client has read in data

    }
}