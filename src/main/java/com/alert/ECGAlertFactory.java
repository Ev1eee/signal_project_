package com.alert;


/**
 *  ECGAlertFactory
 */

public class ECGAlertFactory extends AlertFactory{
    /**
     * Creates an ECG alert with the specified patient ID, condition, and timestamp.
     *
     * @param patientId the ID of the patient associated with the alert
     * @param condition the condition that triggered the alert
     * @param timestamp the time at which the alert was triggered
     * @return an ECG alert

     */
    @Override
    public Alert createAlert(String patientId, String condition, long timestamp) {
        Alert alert=new Alert(patientId,condition,timestamp);
        alert.setType("ECG Alert");
        return alert;
    }
}
