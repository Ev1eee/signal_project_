package com.alert;

import com.data_management.DataStorage;
import com.data_management.Patient;
import com.data_management.PatientRecord;

import java.util.ArrayList;
import java.util.List;

/**
 * The {@code AlertGenerator} class is responsible for monitoring patient data
 * and generating alerts when certain predefined conditions are met. This class
 * relies on a {@link DataStorage} instance to access patient data and evaluate
 * it against specific health criteria.
 */
public class AlertGenerator {
    private DataStorage dataStorage;
    ArrayList<Alert> alerts=new ArrayList<>();

    /**
     * Constructs an {@code AlertGenerator} with a specified {@code DataStorage}.
     * The {@code DataStorage} is used to retrieve patient data that this class
     * will monitor and evaluate.
     *
     * @param dataStorage the data storage system that provides access to patient
     *                    data
     */
    public AlertGenerator(DataStorage dataStorage) {
        this.dataStorage = dataStorage;
    }

    /**
     * Evaluates the specified patient's data to determine if any alert conditions
     * are met. If a condition is met, an alert is triggered via the
     * {@link #triggerAlert}
     * method. This method should define the specific conditions under which an
     * alert
     * will be triggered.
     *
     * @param patient the patient data to evaluate for alert conditions
     */
    public void evaluateData(Patient patient) {
        // Implementation goes here

        List<PatientRecord> patientRecords = patient.getPatientRecords();

        ArrayList<PatientRecord> patientRecords1=new ArrayList<>();//SystolicPressure
        ArrayList<PatientRecord> patientRecords2=new ArrayList<>();//DiastolicPressure
        for (int i=0;i<patientRecords.size();i++){
            if(patientRecords.get(i).getRecordType().equals("ECG")){
                boolean ecg = ECG(patientRecords.get(i));
                if(ecg){
                    Alert alert=new Alert(patient.getPatientId()+"","ECG Warning",System.currentTimeMillis());
                    triggerAlert(alert);
                }
            }
            if(patientRecords.get(i).getRecordType().equals("Saturation")){
                long Starttime=patientRecords.get(i).getTimestamp();
                long EndTime=Starttime+(600*1000);
                for (int i1=0;i1<patientRecords.size();i1++){
                   if( patientRecords.get(i1).getRecordType().equals("Saturation")){
                       if (patient.getPatientRecords().get(i1).getTimestamp()>=Starttime&&patient.getPatientRecords().get(i1).getTimestamp()<=EndTime){
                           if(patient.getPatientRecords().get(i1).getMeasurementValue()<=patientRecords.get(i).getMeasurementValue()-5){
                               Alert alert=new Alert(patient.getPatientId()+"","Saturation Warning",System.currentTimeMillis());
                               triggerAlert(alert);
                               break;
                           }

                       }
                   }
                }



                boolean b = Blood_Saturation(patientRecords.get(i));
                if(b){
                    Alert alert=new Alert(patient.getPatientId()+"","Saturation Warning",System.currentTimeMillis());
                    triggerAlert(alert);
                }
            }
            if(patientRecords.get(i).getRecordType().equals("DiastolicPressure")){
                patientRecords2.add(patientRecords.get(i));
            }
            if(patientRecords.get(i).getRecordType().equals("SystolicPressure")){
                patientRecords1.add(patientRecords.get(i));

            }

        }

        boolean b = Blood_Pressure(patientRecords1, patientRecords2);
        if(b){
            Alert alert=new Alert(patient.getPatientId()+"","Blood_Pressure Warning",System.currentTimeMillis());
            triggerAlert(alert);
        }
        for (int i=0;i<patientRecords.size();i++){
            if(patientRecords.get(i).getRecordType().equals("SystolicPressure")){
                for (int i1=0;i1<patientRecords.size();i1++){
                    if(patientRecords.get(i1).getRecordType().equals("Saturation")){
                        if(patientRecords.get(i).getTimestamp()==patientRecords.get(i1).getTimestamp()){
                            boolean combined = Combined(patientRecords.get(i), patientRecords.get(i1));
                            if (combined){
                                Alert alert=new Alert(patient.getPatientId()+"","Hypotensive Hypoxemia Alert",System.currentTimeMillis());
                                triggerAlert(alert);
                            }
                        }
                    }
                }
            }
        }

    }

    /**
     * Triggers an alert for the monitoring system. This method can be extended to
     * notify medical staff, log the alert, or perform other actions. The method
     * currently assumes that the alert information is fully formed when passed as
     * an argument.
     *
     * @param alert the alert object containing details about the alert condition
     */
    private void triggerAlert(Alert alert) {
        // Implementation might involve logging the alert or notifying staff

        alerts.add(alert);
    }

    /**check ECG  values to determine if an alert should be triggered
     * @param patientRecord
     * @return
     */
    private boolean ECG(PatientRecord patientRecord){
        if(patientRecord.getMeasurementValue()<50||patientRecord.getMeasurementValue()>100){
            return true;
        }

        return false;
    }

    /**
     * check BloodPressure values to determine if an alert should be triggered
     * @param patientRecords SystolicPressure
     * @param patientRecords1 DiastolicPressure
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

    /**
     * check Blood_Saturation values to determine if an alert should be triggered
     * @param patientRecord
     * @return
     */
    public boolean Blood_Saturation(PatientRecord patientRecord){
     if(patientRecord.getMeasurementValue()<92){
         return true;
       }
        return false;
    }

    /**
     * Combined Alert
     * @param patientRecord Saturation
     * @param patientRecord1 SystolicPressure
     * @return
     */
    public boolean Combined(PatientRecord patientRecord,PatientRecord patientRecord1){
        if(patientRecord.getMeasurementValue()<92&&patientRecord1.getMeasurementValue()<90){
            return true;
        }
        if(patientRecord1.getMeasurementValue()<92){
            return true;
        }
        return false;
    }

    /**
     * A Triggered Alert
     *  @param patientRecord
     */
    public void emergency(PatientRecord patientRecord){

        triggerAlert(new Alert(patientRecord.getPatientId()+"","Artificial",System.currentTimeMillis()));
    }

    /**
     * Gets the list of alerts that have been triggered
     *
     * @return the list of triggered alerts
     */
    public ArrayList<Alert> getAlerts() {
        return alerts;
    }
}
