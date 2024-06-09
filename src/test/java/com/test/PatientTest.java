package com.test;

import com.data_management.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PatientTest {

    private Patient patientUnderTest;

    @BeforeEach
    void setUp() {
        patientUnderTest = new Patient(0);
    }

    @Test
    /**
     * Test the addRecord method
     */
    void testGetRecords() {
        DataReader dataReader=new SomeDataReaderImplementation("src/main/java/bin/cc.txt/Saturation.txt");
        DataStorage storage = new DataStorage();

        try {
            dataReader.readData(storage);
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<PatientRecord> records = storage.getRecords(6, 1700000000000L, 1800000000000L);

        assertEquals(3, records.size());

    }
}
