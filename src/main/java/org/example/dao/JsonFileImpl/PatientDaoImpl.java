package org.example.dao.JsonFileImpl;

import org.example.model.Patient;

public class PatientDaoImpl extends JsonDaoImpl<Patient, Long> {
    public PatientDaoImpl(String fileName) {
        super(fileName, Patient.class);
    }

    public Patient getPatientByCIN(String cin) throws Exception{
        return (Patient) this.getAll().stream()
                .filter(patient -> patient.getCIN().toLowerCase().contains(cin))
                .findFirst()
                .orElse(null);
    }
}
