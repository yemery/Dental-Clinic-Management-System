package org.example.presentation.view.frames.Patient;

import org.example.presentation.controller.PatientController;
import org.example.presentation.view.layouts.AppLayout;


public class DeletePatient {
    public DeletePatient(Long id, AppLayout appLayout) {
        PatientController controller = new PatientController();
        controller.deletePatient(id);

        appLayout.getNavbar().simulateTabClick("Patients");
    }
}
