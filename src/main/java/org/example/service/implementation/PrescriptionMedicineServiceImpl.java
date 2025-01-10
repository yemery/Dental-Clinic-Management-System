package org.example.service.implementation;

import org.example.dao.IDao;
import org.example.dao.ArrayListImpl.PrescriptionMedicineDaoImpl;
import org.example.dao.JsonFileImpl.JsonDaoImpl;
import org.example.model.Prescription;
import org.example.model.PrescriptionMedicine;
import org.example.service.api.PrescriptionMedicineService;
import org.example.service.api.PrescriptionService;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class PrescriptionMedicineServiceImpl implements PrescriptionMedicineService {
//    private final IDao<PrescriptionMedicine, Long> dao = new PrescriptionMedicineDaoImpl();
    private final IDao<PrescriptionMedicine, Long> dao = new JsonDaoImpl<>("PrescriptionMedicines.json", PrescriptionMedicine.class);

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
    public void deleteMedicinePrescription(Long ID) {
        try {
            dao.delete(ID);
            this.removeFromPrescription(ID);
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

    @Override
    public boolean removeFromPrescription(Long ID) {
        PrescriptionService prescriptionService = new PrescriptionServiceImpl();
        List<Prescription> prescriptions = prescriptionService.getAllPrescriptions();

        AtomicBoolean updated = new AtomicBoolean(false);
        prescriptions.stream().filter(consultation -> consultation.getPrescriptionsMedicine().contains(ID))
                .forEach(prescription -> {
                    prescription.getPrescriptionsMedicine().remove(ID);
                    prescriptionService.updatePrescription(prescription);
                    updated.set(true);
                });

        return updated.get();

    }

}
