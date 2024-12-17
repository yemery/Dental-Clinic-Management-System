package org.example.presentation.controller;

import org.example.model.Prescription;
import org.example.service.api.PrescriptionService;
import org.example.service.implementation.PrescriptionServiceImpl;

public class PrescriptionController  {
    // we dont update cuz we have static attrs + list
    private final PrescriptionService prescriptionService = new PrescriptionServiceImpl();

    public void addPrescription(Prescription prescription) {
        prescriptionService.addPrescription(prescription);
    }
    public void displayPrescriptions() {
        prescriptionService.getAllPrescriptions().forEach(System.out::println);
    }

    public void deletePrescription(Prescription prescription) {
            prescriptionService.deletePrescription(prescription);
    }

}
