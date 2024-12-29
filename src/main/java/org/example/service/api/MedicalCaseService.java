package org.example.service.api;

import org.example.model.Appointment;
import org.example.model.MedicalCase;
import org.example.model.MedicalHistory;

import java.util.List;

public interface MedicalCaseService {
    MedicalCase addMedicalCase(MedicalCase medicalCase);
    MedicalCase updateMedicalCase(MedicalCase medicalCase);
    MedicalCase getMedicalCase(Long id);
    void deleteMedicalCase(Long ID);
    List<MedicalCase> getAllMedicalCases();

    boolean removeAppointment(MedicalCase medicalCase, Appointment appointment);
    boolean removeMedicalHistory(MedicalCase medicalCase, MedicalHistory medicalHistory);
}
