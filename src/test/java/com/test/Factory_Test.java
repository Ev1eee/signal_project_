package com.test;

import com.alert.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Factory_Test
 */
public class Factory_Test {
    AlertFactory alertFactory;


    @Test
    public void Test_BloodPressure(){
        alertFactory=new BloodPressureAlertFactory();
        Alert test = alertFactory.createAlert("1", "test", System.currentTimeMillis());
        assertEquals("BloodPressure Alert", test.getType());
    }

    @Test
    public void Test_ECG(){
        alertFactory=new ECGAlertFactory();
        Alert test = alertFactory.createAlert("1", "test", System.currentTimeMillis());
        assertEquals("ECG Alert", test.getType());
    }

    @Test
    public void Test_BloodOxygen(){
        alertFactory=new BloodOxygenAlertFactory();
        Alert test = alertFactory.createAlert("1", "test", System.currentTimeMillis());
        assertEquals("Blood Oxygen Alert", test.getType());
    }
}
