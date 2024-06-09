package com.alert;

import com.data_management.Patient;
import com.data_management.PatientRecord;

import java.util.ArrayList;
import java.util.List;

/**
 *to check the blood pressure of the patient whether triggers an alarm
 */

public class BloodPressureStrategy implements AlertStrategy{
    @Override
    /**
     * @param patient the patient whose records are being evaluated
     * @return boolean
     */
    public boolean checkAlert(Patient patient) {

        List<PatientRecord> patientRecords = patient.getPatientRecords();
        ArrayList<PatientRecord> patientRecords1=new ArrayList<>();//Systolic blood pressure
        ArrayList<PatientRecord> patientRecords2=new ArrayList<>();//Diastolic blood pressure
        for (int i=0;i<patientRecords.size();i++){
            if(patientRecords.get(i).getRecordType().equals("DiastolicPressure")){
                patientRecords2.add(patientRecords.get(i));
            }
            if(patientRecords.get(i).getRecordType().equals("SystolicPressure")){
                patientRecords1.add(patientRecords.get(i));

            }

        }

       return Blood_Pressure(patientRecords1, patientRecords2);
    }

    /**
     * Blood_Pressure
     * @param patientRecords Systolic blood pressure
     * @param patientRecords1 Diastolic blood pressure
     * @return
     */
    public boolean Blood_Pressure(ArrayList<PatientRecord> patientRecords,ArrayList<PatientRecord> patientRecords1){

        for (int i=0;i<patientRecords.size();i++){
            if(patientRecords.get(i).getMeasurementValue()>120||patientRecords.get(i).getMeasurementValue()<60){
                return true;
            }
        }

        for (int i=0;i<patientRecords1.size();i++){
            if(patientRecords1.get(i).getMeasurementValue()>180||patientRecords1.get(i).getMeasurementValue()<90){
                return true;
            }
        }

        int x=0;
        for (int i=0;i<patientRecords.size();i++){
            if(i>0) {
                if (patientRecords.get(i).getMeasurementValue() - patientRecords.get(i - 1).getMeasurementValue() >= 10 || patientRecords.get(i).getMeasurementValue() - patientRecords.get(i - 1).getMeasurementValue() <= -10) {
                    x = x + 1;
                }
            }
        }

        if(x>=3){
            return true;
        }

        x=0;

        for (int i=0;i<patientRecords1.size();i++){
            if(i>0) {
                if (patientRecords1.get(i).getMeasurementValue() - patientRecords1.get(i - 1).getMeasurementValue() >= 10 || patientRecords1.get(i).getMeasurementValue() - patientRecords1.get(i - 1).getMeasurementValue() <= -10) {
                    x = x + 1;
                }
            }
        }

        return x >= 3;
    }
}
