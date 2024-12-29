package org.example.service.api;

import org.example.model.Prescription;
import org.example.model.PrescriptionMedicine;

import java.util.List;

public interface PrescriptionService {
    Prescription getPrescriptionById(Long ID);
    Prescription addPrescription(Prescription prescription);
    Prescription updatePrescription(Prescription prescription);
    void deletePrescription(Long ID);
    List<Prescription> getAllPrescriptions();
    public  boolean removePrescriptionMedicine(Prescription prescription, PrescriptionMedicine prescriptionMedicine);
}
