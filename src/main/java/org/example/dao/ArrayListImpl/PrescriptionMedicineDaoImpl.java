package org.example.dao.ArrayListImpl;

import org.example.dao.IDao;
import org.example.model.PrescriptionMedicine;

import java.util.ArrayList;
import java.util.List;

public class PrescriptionMedicineDaoImpl implements IDao<PrescriptionMedicine, Long> {
    private final List<PrescriptionMedicine> prescriptionMedicines = new ArrayList<>();

    @Override
    public List<PrescriptionMedicine> getAll() throws Exception {
        if(prescriptionMedicines.isEmpty()) throw new Exception("Prescription Medicine list is empty");

        return prescriptionMedicines;
    }

    @Override
    public PrescriptionMedicine getById(Long ID) throws Exception {
        return prescriptionMedicines.stream()
                .filter(p -> ID.equals(p.getId()))
                .findFirst().orElseThrow(
                        ()-> new Exception("Prescription Medicine with ID " + ID + " not found")
                );
    }

    @Override
    public PrescriptionMedicine add(PrescriptionMedicine PrescriptionMedicine) throws Exception {
        if (PrescriptionMedicine == null) throw new Exception("Prescription Medicine is null");

        prescriptionMedicines.add(PrescriptionMedicine);
        return PrescriptionMedicine;
    }

    @Override
    public PrescriptionMedicine update(PrescriptionMedicine PrescriptionMedicine) throws Exception {
        PrescriptionMedicine prescriptionMedicineExist = getById(PrescriptionMedicine.getId());

       prescriptionMedicineExist.setMedicine(PrescriptionMedicine.getMedicine());
       prescriptionMedicineExist.setMax(PrescriptionMedicine.getMax());
       prescriptionMedicineExist.setMedicine(PrescriptionMedicine.getMedicine());
       prescriptionMedicineExist.setMedicine(PrescriptionMedicine.getMedicine());

       return prescriptionMedicineExist;
    }

    @Override
    public void delete(PrescriptionMedicine PrescriptionMedicine) throws Exception {
        if(!prescriptionMedicines.contains(PrescriptionMedicine)){
            throw new Exception("Prescription Medicine does not exist");
        }
        prescriptionMedicines.remove(PrescriptionMedicine);
    }
}
