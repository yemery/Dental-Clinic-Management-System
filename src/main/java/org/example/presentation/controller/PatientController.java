package org.example.presentation.controller;

import org.example.model.Patient;
import org.example.service.api.PatientService;
import org.example.service.implementation.PatientServiceImpl;

import java.util.List;

public class PatientController {
    public final PatientService patientService = new PatientServiceImpl();

    public void addPatient(Patient Patient) {
        patientService.addPatient(Patient);
    }
    public List<Patient> displayPatients() {
        return patientService.getPatients();
    }
    public Patient getPatient(Long id) {
        return patientService.getPatient(id);
    }
    public void updatePatient(Patient Patient) {
        patientService.updatePatient(Patient);
    }
    public void deletePatient(Long ID) {
        patientService.deletePatient(ID);
    }

}
