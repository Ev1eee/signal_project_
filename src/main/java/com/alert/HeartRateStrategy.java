package com.alert;

import com.data_management.Patient;

/**
 * Efficiency test
 */
public class HeartRateStrategy implements AlertStrategy{
    @Override
    public boolean checkAlert(Patient patient) {
        for (int i=0;i<patient.getPatientRecords().size();i++){
            if(patient.getPatientRecords().get(i).getRecordType().equals("ECG")){
                if(patient.getPatientRecords().get(i).getMeasurementValue()<50||patient.getPatientRecords().get(i).getMeasurementValue()>100){
                    return true;
                }
            }
        }
        return false;
    }
}
