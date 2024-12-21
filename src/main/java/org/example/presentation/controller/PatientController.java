package org.example.presentation.controller;

import org.example.model.Patient;
import org.example.service.api.PatientService;
import org.example.service.implementation.PatientServiceImpl;

public class PatientController {
    public final PatientService patientService = new PatientServiceImpl();

    public void addPatient(Patient Patient) {
        patientService.addPatient(Patient);
    }
    public void displayPatient() {
        patientService.getPatients().forEach(System.out::println);
    }
    public void updatePatient(Patient Patient) {
        patientService.updatePatient(Patient);
    }
    public void deletePatient(Patient Patient) {
        patientService.deletePatient(Patient);
    }

}
