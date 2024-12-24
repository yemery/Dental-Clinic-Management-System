package org.example.presentation.controller;

import org.example.model.*;
import org.example.service.api.MedicalCaseService;
import org.example.service.implementation.MedicalCaseImpl;

public class MedicalCaseController {
    private final MedicalCaseService medicalCaseService = new MedicalCaseImpl();

    public void addMedicalCase(MedicalCase medicalCase) {
        medicalCaseService.addMedicalCase(medicalCase);
    }
    public void deleteMedicalCase(MedicalCase medicalCase) {
        medicalCaseService.deleteMedicalCase(medicalCase);
    }
    public void updateMedicalCase(MedicalCase medicalCase) {
        medicalCaseService.updateMedicalCase(medicalCase);
    }
    public void getAllMedicalCase() {
            medicalCaseService.getAllMedicalCases().forEach(System.out::println);
    }
    public void getMedicalCaseById(Long id) {
        medicalCaseService.getMedicalCase(id);
    }

    public boolean removeAppointment(MedicalCase medicalCase,Appointment appointment) {
        return medicalCaseService.removeAppointment(medicalCase, appointment);
    }

    public boolean removeMedicalHistory(MedicalCase medicalCase, MedicalHistory medicalHistory) {
        return medicalCaseService.removeMedicalHistory(medicalCase, medicalHistory);
    }
}
