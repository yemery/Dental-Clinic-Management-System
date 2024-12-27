package org.example.dao.ArrayListImpl;

import org.example.dao.IDao;
import org.example.model.MedicalHistory;

import java.util.ArrayList;
import java.util.List;

public class MedicalHistoryDaoImpl implements IDao<MedicalHistory,Long> {
    private final List<MedicalHistory> medicsHistories = new ArrayList<>();

    @Override
    public List<MedicalHistory> getAll() throws Exception {
        if (medicsHistories.isEmpty()) throw new Exception("No medical history found");
        return medicsHistories;
    }

    @Override
    public MedicalHistory getById(Long ID) throws Exception {
        return medicsHistories
                .stream()
                .filter(x -> x.getId().equals(ID))
                .findFirst()
                .orElseThrow(()-> new Exception("No medical history found with ID: " + ID));
    }

    @Override
    public MedicalHistory add(MedicalHistory medicalHistory) throws Exception {
        if (medicalHistory == null) throw new Exception("Error while trying to add a MedicalHistory");
        medicsHistories.add(medicalHistory);
        return medicalHistory;
    }

    @Override
    public MedicalHistory update(MedicalHistory newRec) throws Exception {
        MedicalHistory record = getById(newRec.getId());

        record.setLabel(newRec.getLabel());
        record.setCategory(newRec.getCategory());
        record.setDescription(newRec.getDescription());
        record.setRisk(newRec.getRisk());

        return record;
    }

    @Override
    public void delete(MedicalHistory medicalHistory) throws Exception {
        if (!medicsHistories.contains(medicalHistory))
            throw new Exception("Medical History does not exist");

        medicsHistories.remove(medicalHistory);
        System.out.println("Medical History deleted");
    }
}
