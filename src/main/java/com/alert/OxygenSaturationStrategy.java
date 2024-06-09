package com.alert;

import com.data_management.Patient;


/**
 * Oxygen saturation detection
 */
public class OxygenSaturationStrategy implements AlertStrategy{
    @Override
    public boolean checkAlert(Patient patient) {
        for (int i=0;i<patient.getPatientRecords().size();i++){


            long Starttime=patient.getPatientRecords().get(i).getTimestamp();
            long EndTime=Starttime+(600*1000);
            for (int i1=0;i1<patient.getPatientRecords().size();i1++){
                if( patient.getPatientRecords().get(i1).getRecordType().equals("Saturation")){
                    if (patient.getPatientRecords().get(i1).getTimestamp()>=Starttime&&patient.getPatientRecords().get(i1).getTimestamp()<=EndTime){
                        if(patient.getPatientRecords().get(i1).getMeasurementValue()<=patient.getPatientRecords().get(i).getMeasurementValue()-5){
                         return true;
                        }

                    }
                }
            }

            if(patient.getPatientRecords().get(i).getRecordType().equals("Saturation")){
                if(patient.getPatientRecords().get(i).getMeasurementValue()<92){
                    return true;
                }
            }
        }
        return false;
    }
}
