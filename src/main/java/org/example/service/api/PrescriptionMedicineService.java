package org.example.service.api;

import org.example.model.PrescriptionMedicine;

import java.util.List;

public interface PrescriptionMedicineService {
    PrescriptionMedicine addMedicinePrescription(PrescriptionMedicine prescription);
    PrescriptionMedicine getMedicinePrescription(Long ID);
    PrescriptionMedicine updateMedicinePrescription(PrescriptionMedicine prescription);
    void deleteMedicinePrescription(Long ID);
    List<PrescriptionMedicine> getAllMedicinePrescription();

    boolean removeFromPrescription(Long ID);
}
