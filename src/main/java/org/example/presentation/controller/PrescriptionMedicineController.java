package org.example.presentation.controller;

import org.example.model.PrescriptionMedicine;
import org.example.service.api.PrescriptionMedicineService;
import org.example.service.implementation.PrescriptionMedicineServiceImpl;

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

    public void displayAllPrescriptionMedicine() {
        prescriptionMedicineService.getAllMedicinePrescription().forEach(System.out::println);
    }
}
