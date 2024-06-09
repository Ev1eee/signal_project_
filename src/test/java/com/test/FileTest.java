package com.test;

import com.cardio_generator.HealthDataSimulator;
import org.junit.jupiter.api.Test;

import java.io.IOException;
/**
 * FileTest
 */

public class FileTest {
    @Test
    public void testBeServer() throws IOException {
        HealthDataSimulator.main(new String[]{"--output","file:src/main/java/bin/cc.txt/Saturation-temp.txt"});
    }
}
