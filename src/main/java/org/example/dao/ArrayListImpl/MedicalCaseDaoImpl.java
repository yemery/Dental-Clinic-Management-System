package org.example.dao.ArrayListImpl;

import org.example.dao.IDao;
import org.example.model.Invoice;
import org.example.model.MedicalCase;

import java.util.ArrayList;
import java.util.List;

public class MedicalCaseDaoImpl  implements IDao<MedicalCase,Long> {
    private final List<MedicalCase> medicalCases = new ArrayList<MedicalCase>();
    @Override
    public List<MedicalCase> getAll() throws Exception {
        if (medicalCases.isEmpty())
            throw new Exception("No medical cases found");
        return medicalCases;
    }

    @Override
    public MedicalCase getById(Long ID) throws Exception {
        return medicalCases.stream().filter(
                medicalCase -> ID.equals(medicalCase.getId())
        ).findFirst().orElseThrow(()->new Exception("No medical case found"));
    }

    @Override
    public MedicalCase add(MedicalCase medicalCase) throws Exception {
        if (medicalCase == null) throw new Exception("No medical case found");
        medicalCases.add(medicalCase);
        return medicalCase;
    }

    @Override
    public MedicalCase update(MedicalCase medicalCase) throws Exception {
        MedicalCase existingMedicalCase = getById(medicalCase.getId());
        existingMedicalCase.setCreationDate(medicalCase.getCreationDate());
        existingMedicalCase.setPatient(medicalCase.getPatient());
        return existingMedicalCase;

    }

    @Override
    public void delete(Long ID) throws Exception {
        MedicalCase existingMedicalCase = this.getById(ID);

        medicalCases.remove(existingMedicalCase);

    }
}
