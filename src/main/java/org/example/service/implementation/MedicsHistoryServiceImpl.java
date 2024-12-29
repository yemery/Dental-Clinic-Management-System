package org.example.service.implementation;

import org.example.dao.IDao;
import org.example.dao.ArrayListImpl.MedicalHistoryDaoImpl;
import org.example.model.MedicalHistory;
import org.example.service.api.MedicslHistoryService;

import java.util.List;

public class MedicsHistoryServiceImpl implements MedicslHistoryService {
    private final IDao<MedicalHistory, Long> dao = new MedicalHistoryDaoImpl();

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
}
