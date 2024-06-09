package com.test;

import com.cardio_generator.HealthDataSimulator;
import com.data_management.DataStorage;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Singleton test
 */
public class Singleton {

    HealthDataSimulator healthDataSimulator;
    DataStorage dataStorage;


    @BeforeEach
    public void init() {
        healthDataSimulator = new HealthDataSimulator();
        dataStorage = new DataStorage();
    }


    @Test
    public void DataStorage_Test() {
        DataStorage instance1 = dataStorage.getInstance();
        DataStorage instance2 = dataStorage.getInstance();
        assertEquals(instance1, instance2);
    }

    @Test
    public void HealthDataSimulator_Test(){
        HealthDataSimulator healthDataSimulator_Test1=healthDataSimulator.getInstance();
        HealthDataSimulator healthDataSimulator_Test2=healthDataSimulator.getInstance();
        assertEquals(healthDataSimulator_Test1, healthDataSimulator_Test2);
    }
}
