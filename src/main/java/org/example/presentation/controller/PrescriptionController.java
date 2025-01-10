package org.example.presentation.controller;

import org.example.model.Prescription;
import org.example.model.PrescriptionMedicine;
import org.example.service.api.PrescriptionService;
import org.example.service.implementation.PrescriptionServiceImpl;

public class PrescriptionController  {
    private final PrescriptionService prescriptionService = new PrescriptionServiceImpl();

    public void addPrescription(Prescription prescription) {
        prescriptionService.addPrescription(prescription);
    }

    public void displayPrescriptions() {
        prescriptionService.getAllPrescriptions().forEach(System.out::println);
    }

    public void deletePrescription(Long ID) {
            prescriptionService.deletePrescription(ID);
    }

    public void updatePrescription(Prescription prescription) {
        prescriptionService.updatePrescription(prescription);
    }

    public Prescription getPrescription(Long ID) {return prescriptionService.getPrescriptionById(ID);}
}
