package org.example.service.implementation;

import org.example.dao.IDao;
import org.example.dao.ArrayListImpl.MedicalHistoryDaoImpl;
import org.example.dao.JsonFileImpl.JsonDaoImpl;
import org.example.model.MedicalCase;
import org.example.model.MedicalHistory;
import org.example.model.PrescriptionMedicine;
import org.example.service.api.MedicalCaseService;
import org.example.service.api.MedicslHistoryService;
import org.example.service.api.PrescriptionMedicineService;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class MedicsHistoryServiceImpl implements MedicslHistoryService {
//    private final IDao<MedicalHistory, Long> dao = new MedicalHistoryDaoImpl();
    private final IDao<MedicalHistory, Long> dao = new JsonDaoImpl<>("MedicsHistory.json",MedicalHistory.class);

    @Override
    public List<MedicalHistory> getAllMedicsHistory() {
        try{
            return dao.getAll();
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteMedicsHistory(Long ID) {
        try{
            dao.delete(ID);
            this.removeMHFromMedicalCase(ID);
        }
        catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public MedicalHistory updateMedicsHistory(MedicalHistory mh) {
        try {
            dao.update(mh);
            System.out.println("Medics history updated");
            return mh;
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public MedicalHistory getMedicsHistory(Long ID) {
        try {
            return dao.getById(ID);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public MedicalHistory addMedicsHistory(MedicalHistory mh) {
        try {
            MedicalHistory medicalHis = dao.add(mh);
            System.out.println("Medics History added successfully");
            return medicalHis;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean removeMHFromMedicalCase(Long ID) {
        MedicalCaseService medicalCaseService= new MedicalCaseImpl();
        List<MedicalCase> medicalCaseList= medicalCaseService.getAllMedicalCases();

        AtomicBoolean updated = new AtomicBoolean(false);
        medicalCaseList.stream().filter(mc -> mc.getMedicalHistories().contains(ID))
                .forEach(pm -> {
// LATER TEST
                    pm.getMedicalHistories().remove(ID);
                    medicalCaseService.updateMedicalCase(pm);
                    updated.set(true);
                });

        return updated.get();

    }
}
