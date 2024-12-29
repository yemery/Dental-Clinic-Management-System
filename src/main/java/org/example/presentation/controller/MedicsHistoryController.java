package org.example.presentation.controller;

import org.example.model.MedicalHistory;
import org.example.service.api.MedicslHistoryService;
import org.example.service.implementation.MedicsHistoryServiceImpl;

public class MedicsHistoryController {
    private final MedicslHistoryService mhService = new MedicsHistoryServiceImpl();

    public void addMHistory(MedicalHistory mh) {mhService.addMedicsHistory(mh);}
    public void showAllMHistories(){mhService.getAllMedicsHistory().forEach(System.out::println);}
    public void updateMHistory(MedicalHistory mh) {mhService.updateMedicsHistory(mh);}
    public void deleteMHistory(Long ID) {mhService.deleteMedicsHistory(ID);}
}
