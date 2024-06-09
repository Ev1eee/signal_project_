package com.test;

import com.alert.BloodPressureStrategy;
import com.alert.OxygenSaturationStrategy;
import com.alert.StrategyContext;
import com.cardio_generator.outputs.*;
import com.data_management.Patient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class StrategyTest {
    @Test
    public void testBloodPressureAlert(){
        Patient patient=new Patient(1);
        boolean actual=new StrategyContext(new BloodPressureStrategy()).doStrategy(patient);
        boolean except=false;
        Assertions.assertEquals(actual,except);
    }

    @Test
    public void testOxygenSaturationAlert(){
        Patient patient=new Patient(1);
        boolean actual=new StrategyContext(new OxygenSaturationStrategy()).doStrategy(patient);
        boolean except=false;
        Assertions.assertEquals(actual,except);
    }

    @Test
    public void testConsoleOutputStrategy(){
        new OutputStrategyContext(new ConsoleOutputStrategy()).doStrategy(1,1L,"test","data");
    }

    @Test
    public void testFileOutputStrategy(){
        new OutputStrategyContext(new FileOutputStrategy("src/main/java/bin/cc.txt/Saturation-temp.txt")).
                doStrategy(1,1L,"test","data");
    }

    @Test
    public void testTcpOutputStrategy(){
        new OutputStrategyContext(new TcpOutputStrategy(9997)).
                doStrategy(1,1L,"test","data");
    }

    @Test
    public void testWebSocketOutputStrategy(){
        new OutputStrategyContext(new WebSocketOutputStrategy(9996)).
                doStrategy(1,1L,"test","data");
    }
}
