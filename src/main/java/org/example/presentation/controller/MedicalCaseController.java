package org.example.presentation.controller;

import org.example.model.*;
import org.example.service.api.MedicalCaseService;
import org.example.service.implementation.MedicalCaseImpl;

import java.util.List;

public class MedicalCaseController {
    private final MedicalCaseService medicalCaseService = new MedicalCaseImpl();

    public void addMedicalCase(MedicalCase medicalCase) {
        medicalCaseService.addMedicalCase(medicalCase);
    }
    public void deleteMedicalCase(Long ID) {
        medicalCaseService.deleteMedicalCase(ID);
    }
    public void updateMedicalCase(MedicalCase medicalCase) {
        medicalCaseService.updateMedicalCase(medicalCase);
    }
    public List<MedicalCase> getAllMedicalCase() {
            return medicalCaseService.getAllMedicalCases();
    }
    public MedicalCase getMedicalCaseById(Long id) {
        return medicalCaseService.getMedicalCase(id);
    }

    public boolean removeAppointment(MedicalCase medicalCase,Appointment appointment) {
        return medicalCaseService.removeAppointment(medicalCase, appointment);
    }

    public boolean removeMedicalHistory(MedicalCase medicalCase, MedicalHistory medicalHistory) {
        return medicalCaseService.removeMedicalHistory(medicalCase, medicalHistory);
    }
}
