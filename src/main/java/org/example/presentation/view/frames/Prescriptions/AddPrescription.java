package org.example.presentation.view.frames.Prescriptions;

import org.example.model.Prescription;
import org.example.presentation.controller.PrescriptionController;
import org.example.presentation.view.frames.Frame;
import org.example.presentation.view.layouts.AppLayout;

public class AddPrescription extends Frame {
    private AppLayout appLayout;

    public AddPrescription(AppLayout appLayout) {
        PrescriptionController controller = new PrescriptionController();
        controller.addPrescription(new Prescription());

        appLayout.getNavbar().simulateTabClick("Prescriptions");
    }

}
