package com.data_management;

import org.java_websocket.client.WebSocketClient;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 *   Uncommented code is for reading from a file
 */

public class SomeDataReaderImplementation implements DataReader{

    private String File_path;

    public SomeDataReaderImplementation(String filepath){
        this.File_path=filepath;
    }
    @Override

    /**
     * Read data
     * @param dataStorage the storage where data will be stored
     * @throws IOException if there is an error reading the data
     */
    public void readData(DataStorage dataStorage) throws IOException {
        try {
            BufferedReader in = new BufferedReader(new FileReader(File_path));
            String str;
            while ((str = in.readLine()) != null) {
                // Data reading logic
                String[] split = str.split(",");
                try {
                    dataStorage.addPatientData(Integer.parseInt(split[0].split(": ")[1]),Double.parseDouble(split[3].split(": ")[1].split("%")[0]),split[2].split(":")[1], Long.parseLong(split[1].split(": ")[1]));
                } catch (NumberFormatException e) {
                    System.out.println("Failed to read file");
                }

            }

        } catch (IOException e) {
            System.out.println("Failed to read file");
        }
//        WebSocketClient1 webSocketClient1= null;
//        try {
//            webSocketClient1 = new WebSocketClient1(new URI("ws://localhost:9999")); // Initialize the client
//        } catch (URISyntaxException e) {
//            System.out.println("Failed to start client");
//        }
//        webSocketClient1.setDataStorage(dataStorage); // Set the client to read data into the parser
//        webSocketClient1.connect(); // Start the client

        // Block the thread, waiting for the server data transfer to complete
//        while (true){
//
//            if (webSocketClient1.is()){ // Check if the server has disconnected
//
//                break;
//            }
//
//        }

    }

//    /**
//     * Read data from the server
//     * @throws URISyntaxException
//     */
//    @Override
//    public void readDate_Socket() throws URISyntaxException {
//
//
//    }
}