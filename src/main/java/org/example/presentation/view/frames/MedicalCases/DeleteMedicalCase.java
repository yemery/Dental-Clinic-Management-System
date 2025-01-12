package org.example.presentation.view.frames.MedicalCases;

import org.example.presentation.controller.MedicalCaseController;
import org.example.presentation.view.layouts.AppLayout;

public class DeleteMedicalCase {
    public DeleteMedicalCase(Long id, AppLayout appLayout) {
        MedicalCaseController controller = new MedicalCaseController();
        controller.deleteMedicalCase(id);

        appLayout.getNavbar().simulateTabClick("MedicalCases");
    }
}
