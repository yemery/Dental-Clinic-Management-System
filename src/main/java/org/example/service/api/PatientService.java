package org.example.service.api;

import org.example.model.Act;
import org.example.model.Patient;

import java.util.List;

public interface PatientService {
    Patient addPatient(Patient act);

    Patient getPatient(Long id);

    Patient updatePatient(Patient act);

    void deletePatient(Long ID);

    List<Patient> getPatients();

    List<Patient> getPatientByCin(String cin);
}
