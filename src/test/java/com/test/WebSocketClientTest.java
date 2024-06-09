package com.test;

import com.cardio_generator.HealthDataSimulator;
import com.data_management.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.Set;

import static com.data_management.DataStorage.UP_Date;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WebSocketClientTest {

    /**
     * Test whether the server starts up correctly.
     * @throws IOException
     */
    @Test
    public void testBeServer() throws IOException {
        HealthDataSimulator.main(new String[]{"--output","websocket:9999"});
        WebSocketClient1 webSocketClient1= null;
        try {
            webSocketClient1 = new WebSocketClient1(new URI("ws://localhost:9999")); // Initialize the client
        } catch (URISyntaxException e) {
            System.out.println("Failed to start client");
        }
        webSocketClient1.setDataStorage(new DataStorage()); // Set the client to read data into the parser
        webSocketClient1.connect(); // Start the client
        webSocketClient1.is();
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