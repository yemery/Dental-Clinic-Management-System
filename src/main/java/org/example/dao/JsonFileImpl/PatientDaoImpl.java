package org.example.dao.JsonFileImpl;

import org.example.model.Patient;

public class PatientDaoImpl extends JsonDaoImpl<Patient, Long> {
    public PatientDaoImpl(String fileName) {
        super(fileName, Patient.class);
    }
}
