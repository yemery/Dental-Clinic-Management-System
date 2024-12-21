package org.example.service.api;

import org.example.model.MedicalCase;

import java.util.List;

public interface MedicalCaseService {
    MedicalCase addMedicalCase(MedicalCase medicalCase);
    MedicalCase updateMedicalCase(MedicalCase medicalCase);
    MedicalCase getMedicalCase(Long id);
    void deleteMedicalCase(MedicalCase medicalCase);
    List<MedicalCase> getAllMedicalCases();
}
