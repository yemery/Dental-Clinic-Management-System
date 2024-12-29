package org.example.service.api;

import org.example.model.MedicalHistory;

import java.util.List;

public interface MedicslHistoryService {
    MedicalHistory addMedicsHistory(MedicalHistory mh);
    MedicalHistory getMedicsHistory(Long ID);
    MedicalHistory updateMedicsHistory(MedicalHistory mh);
    void deleteMedicsHistory(Long ID);
    List<MedicalHistory> getAllMedicsHistory();
}
