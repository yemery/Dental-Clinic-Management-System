package org.example.dao.JsonFileImpl;

import org.example.model.Patient;

import java.util.List;
import java.util.stream.Collectors;

public class PatientDaoImpl extends JsonDaoImpl<Patient, Long> {
    public PatientDaoImpl(String fileName) {
        super(fileName, Patient.class);
    }

    public List<Patient> getPatientByCin(String cin) throws Exception {

        return getAll().stream()
                .filter(patient -> patient.getCIN().toLowerCase().contains(cin))
                .collect(Collectors.toList());
    }
}
