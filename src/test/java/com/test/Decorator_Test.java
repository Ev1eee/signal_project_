package com.test;

import com.alert.Alert;
import com.alert.RepeatedAlertDecorator;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Decorator_Test
 */
public class Decorator_Test {

    Alert alert;

    @BeforeEach
    public void init(){
        alert=new Alert("1","test",System.currentTimeMillis());
    }


    /**
     *  you can duplicate the current alert and return a new alert, achieving the effect of repeated alerts
     */
    @Test
    public void Test_Alert(){
        RepeatedAlertDecorator repeatedAlertDecorator = new RepeatedAlertDecorator(alert);
        boolean alert_test=repeatedAlertDecorator.alert();
        assertTrue(alert_test);
    }
}
