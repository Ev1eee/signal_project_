package com.alert;

import com.data_management.Patient;

/**
 * Strategy pattern interface
 * Check whether the data triggers an alarm
 *
 */
public interface AlertStrategy {
   boolean  checkAlert(Patient patient);
}
