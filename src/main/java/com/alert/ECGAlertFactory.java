package com.alert;

public class ECGAlertFactory extends AlertFactory{

    @Override
    public Alert createAlert(String patientId, String condition, long timestamp) {
        Alert alert=new Alert(patientId,condition,timestamp);
        alert.setType("ECG Alert");
        return alert;
    }
}
