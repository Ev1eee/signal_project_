package com.test;

import com.alert.Alert;
import com.alert.AlertGenerator;
import com.data_management.*;
import org.junit.jupiter.api.Test;


import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;


public class AlertGeneratorTest {


   @Test
    public void testEvaluateData_Test(){
        AlertGenerator alertGenerator=new AlertGenerator(new DataStorage());
        DataReader dataReader=new SomeDataReaderImplementation("src/main/java/bin/cc.txt/ECG.txt");
        DataReader dataReader1=new SomeDataReaderImplementation("src/main/java/bin/cc.txt/Saturation.txt");
        DataReader dataReader2=new SomeDataReaderImplementation("src/main/java/bin/cc.txt/DiastolicPressure.txt");
        DataReader dataReader3=new SomeDataReaderImplementation("src/main/java/bin/cc.txt/SystolicPressure.txt");
        DataStorage storage = new DataStorage();
        try {
           dataReader.readData(storage);
           dataReader1.readData(storage);
           dataReader2.readData(storage);
           dataReader3.readData(storage);
        } catch (IOException e) {
           e.printStackTrace();
        }
        for (int i=0;i<storage.getAllPatients().size();i++){
           alertGenerator.evaluateData(storage.getAllPatients().get(i));
        }

        assertEquals(271, alertGenerator.getAlerts().size()); // Check if two records are retrieved
        Alert alert=new Alert("1","condition",10000L);
        assertEquals(alert.getPatientId(),"1");
        assertEquals(alert.getCondition(),"condition");
        assertEquals(alert.getTimestamp(),10000L);

        ArrayList<PatientRecord> patientRecords=new ArrayList<>();
        patientRecords.add(new PatientRecord(1,60,"",1L));
        patientRecords.add(new PatientRecord(1,70,"",2L));
        patientRecords.add(new PatientRecord(1,80,"",3L));
        patientRecords.add(new PatientRecord(1,90,"",4L));
        assertEquals(true,alertGenerator.Blood_Pressure(patientRecords,new ArrayList<>()));

       ArrayList<PatientRecord> patientRecords1=new ArrayList<>();
       patientRecords1.add(new PatientRecord(1,90,"",1L));
       patientRecords1.add(new PatientRecord(1,100,"",2L));
       patientRecords1.add(new PatientRecord(1,110,"",3L));
       patientRecords1.add(new PatientRecord(1,120,"",4L));
       assertEquals(true,alertGenerator.Blood_Pressure(new ArrayList<>(),patientRecords1));

       Patient patient=new Patient(1);
       patient.addRecord(80,"Saturation",1L);
       alertGenerator.evaluateData(patient);
       assertEquals(272, alertGenerator.getAlerts().size());

       patient=new Patient(1);
       patient.addRecord(90,"SystolicPressure",1L);
       patient.addRecord(80,"Saturation",1L);
       alertGenerator.evaluateData(patient);
       assertEquals(274, alertGenerator.getAlerts().size());

       patient=new Patient(1);
       patient.addRecord(90,"ECG",1L);
       alertGenerator.evaluateData(patient);
       assertEquals(274, alertGenerator.getAlerts().size());

       assertEquals(true,alertGenerator.Combined(new PatientRecord(1,90,"",1L),
               new PatientRecord(1,91,"",1L)));

       alertGenerator.exigency(new PatientRecord(1,91,"",1L));

       patientRecords=new ArrayList<>();
       patientRecords.add(new PatientRecord(1,90,"",1L));
       patientRecords.add(new PatientRecord(1,80,"",2L));
       patientRecords.add(new PatientRecord(1,70,"",3L));
       patientRecords.add(new PatientRecord(1,60,"",4L));
       assertEquals(true,alertGenerator.Blood_Pressure(patientRecords,new ArrayList<>()));

       patientRecords1=new ArrayList<>();
       patientRecords1.add(new PatientRecord(1,120,"",1L));
       patientRecords1.add(new PatientRecord(1,110,"",2L));
       patientRecords1.add(new PatientRecord(1,100,"",3L));
       patientRecords1.add(new PatientRecord(1,90,"",4L));
       assertEquals(true,alertGenerator.Blood_Pressure(new ArrayList<>(),patientRecords1));
   }
}
