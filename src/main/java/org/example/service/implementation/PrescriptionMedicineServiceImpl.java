package org.example.service.implementation;

import org.example.dao.IDao;
import org.example.dao.implementation.PrescriptionMedicineDaoImpl;
import org.example.model.PrescriptionMedicine;
import org.example.service.api.PrescriptionMedicineService;

import java.util.List;

public class PrescriptionMedicineServiceImpl implements PrescriptionMedicineService {
    private final IDao<PrescriptionMedicine, Long> dao = new PrescriptionMedicineDaoImpl();

    @Override
    public PrescriptionMedicine addMedicinePrescription(PrescriptionMedicine prescription) {
        try {
            PrescriptionMedicine pm = dao.add(prescription);
            System.out.println("prescription medicine added");
            return pm;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public PrescriptionMedicine getMedicinePrescription(Long ID) {
        try{
            return dao.getById(ID);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public PrescriptionMedicine updateMedicinePrescription(PrescriptionMedicine prescription) {
        try{
            return dao.update(prescription);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteMedicinePrescription(PrescriptionMedicine prescription) {
        try {
            dao.delete(prescription);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<PrescriptionMedicine> getAllMedicinePrescription() {
        try {
            return dao.getAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
