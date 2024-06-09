package com.alert;

/**
 *  factory pattern to create alerts
 */
public abstract class AlertFactory {
    public abstract Alert createAlert(String patientId, String condition, long timestamp);
}
