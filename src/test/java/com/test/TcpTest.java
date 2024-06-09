package com.test;

import com.cardio_generator.HealthDataSimulator;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.Socket;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TcpTest {

    /**
     * Test whether the server starts up correctly.
     * @throws IOException
     */
    @Test
    public void testBeServer() throws IOException {
        HealthDataSimulator.main(new String[]{"--patient-count","12","--output","tcp:9998"});
        Socket socket = new Socket("localhost",9998);

    }
}