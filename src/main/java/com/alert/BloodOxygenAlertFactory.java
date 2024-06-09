package com.alert;

public class BloodOxygenAlertFactory extends AlertFactory{
    @Override
    public Alert createAlert(String patientId, String condition, long timestamp) {
        Alert alert=new Alert(patientId,condition,timestamp);
        alert.setType("Blood Oxygen Alert");
        return alert;
    }
}
