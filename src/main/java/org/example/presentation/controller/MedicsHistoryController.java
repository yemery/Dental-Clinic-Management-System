package org.example.presentation.controller;

import org.example.model.MedicalHistory;
import org.example.service.api.MedicslHistoryService;
import org.example.service.implementation.MedicsHistoryServiceImpl;

import java.util.List;

public class MedicsHistoryController {
    private final MedicslHistoryService mhService = new MedicsHistoryServiceImpl();

    public void addMHistory(MedicalHistory mh) {mhService.addMedicsHistory(mh);}
    public List<MedicalHistory> showAllMHistories(){return mhService.getAllMedicsHistory();}
    public void updateMHistory(MedicalHistory mh) {mhService.updateMedicsHistory(mh);}
    public void deleteMHistory(Long ID) {mhService.deleteMedicsHistory(ID);}
    public MedicalHistory getMh(Long ID){
        return mhService.getMedicsHistory(ID);
    }
}
