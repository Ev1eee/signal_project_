package com.data_management;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.java_websocket.server.WebSocketServer;

import java.net.URI;

/**
 *  reads data from the server
 */

public class WebSocketClient1 extends WebSocketClient {

    DataStorage dataStorage; // Handles data

    boolean is_Read_OK = false; // Whether the read is complete

    /**
     * Initialize connection
     *
     */
    public WebSocketClient1(URI serverUri) {
        super(serverUri);
    }

    /**
     * Read data into dataStorage
     * @param dataStorage
     */
    public void setDataStorage(DataStorage dataStorage) {
        this.dataStorage = dataStorage;
    }

    /**
     * Check if data reading is complete
     * @return
     */
    public boolean is() {
        return is_Read_OK;
    }

    @Override
    public void onOpen(ServerHandshake serverHandshake) {

    }

    @Override
    public void onMessage(String s) {

        try {

            String s1 = s.split(",")[3];
            dataStorage.addPatientData(Integer.parseInt(s.split(",")[0]), Double.parseDouble(s1.split("%")[0]), s.split(",")[2], Long.parseLong(s.split(",")[1]));
        } catch (NumberFormatException e) {
            this.close();

            System.out.println("Data error, client closing");
            is_Read_OK = true;
        }


    }

    @Override
    public void onClose(int i, String s, boolean b) {
        is_Read_OK = true;
    }

    @Override
    public void onError(Exception e) {
        System.out.println("Server error");
        is_Read_OK = true;
    }
}