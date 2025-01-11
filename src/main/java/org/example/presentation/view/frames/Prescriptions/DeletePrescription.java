package org.example.presentation.view.frames.Prescriptions;

import org.example.presentation.controller.PrescriptionController;
import org.example.presentation.view.layouts.AppLayout;

public class DeletePrescription {
    public DeletePrescription(Long id, AppLayout appLayout) {
        PrescriptionController controller = new PrescriptionController();
        controller.deletePrescription(id);

        appLayout.getNavbar().simulateTabClick("Prescriptions");
    }
}
