package org.example.presentation.controller;

import org.example.model.PrescriptionMedicine;
import org.example.service.api.PrescriptionMedicineService;
import org.example.service.implementation.PrescriptionMedicineServiceImpl;

import java.util.List;

public class PrescriptionMedicineController {
    private final PrescriptionMedicineService prescriptionMedicineService = new PrescriptionMedicineServiceImpl();

    public void addPrescriptionMedicine(PrescriptionMedicine pMedicine) {
        prescriptionMedicineService.addMedicinePrescription(pMedicine);
    }

    public void updatePrescriptionMedicine(PrescriptionMedicine pMedicine) {
        prescriptionMedicineService.updateMedicinePrescription(pMedicine);
    }

    public void deletePrescriptionMedicine(Long ID) {
        prescriptionMedicineService.deleteMedicinePrescription(ID);
    }

    public List<PrescriptionMedicine> displayAllPrescriptionMedicine() {
        return prescriptionMedicineService.getAllMedicinePrescription();
    }

    public PrescriptionMedicine getPrescriptionMedicine(Long ID) {return  prescriptionMedicineService.getMedicinePrescription(ID);}
}
