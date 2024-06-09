package com.alert;

public class BloodPressureAlertFactory extends AlertFactory{
    @Override
    /**
     * @param patientId
     * @param condition
     * @param timestamp
     * @return Alert
     */
    public Alert createAlert(String patientId, String condition, long timestamp) {

        Alert alert=new Alert(patientId,condition,timestamp);
        alert.setType("BloodPressure Alert");
        return alert;
    }
}
